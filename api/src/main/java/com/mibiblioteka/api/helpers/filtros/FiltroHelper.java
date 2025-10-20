package com.mibiblioteka.api.helpers.filtros;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Helper reutilizable para construir consultas dinámicas de MongoDB
 * a partir de un mapa de filtros.
 */
@Component
public class FiltroHelper {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Construye una consulta MongoDB genérica según los filtros enviados.
     * Si no se envían filtros, retorna una consulta vacía (trae todos los
     * registros).
     */
    public static <T> Query construirQuery(Map<String, Object> filtros, Class<T> entityClass) {
        Query query = new Query();

        if (filtros != null && !filtros.isEmpty()) {
            Criteria criterios = parseFiltros(filtros);
            if (criterios != null) {
                query.addCriteria(criterios);
            }
        }

        return query;
    }

    /**
     * Procesa los filtros y construye los criterios principales.
     */
    @SuppressWarnings("unchecked")
    private static Criteria parseFiltros(Map<String, Object> filtros) {
        List<Criteria> criteriaList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filtros.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            try {
                if ("AND".equalsIgnoreCase(key) && value instanceof List<?> andList) {
                    List<Criteria> subCriterias = new ArrayList<>();
                    for (Object subMap : andList) {
                        if (subMap instanceof Map<?, ?>) {
                            subCriterias.add(parseFiltros((Map<String, Object>) subMap));
                        }
                    }
                    criteriaList.add(new Criteria().andOperator(subCriterias.toArray(new Criteria[0])));
                } else if ("OR".equalsIgnoreCase(key) && value instanceof List<?> orList) {
                    List<Criteria> subCriterias = new ArrayList<>();
                    for (Object subMap : orList) {
                        if (subMap instanceof Map<?, ?>) {
                            subCriterias.add(parseFiltros((Map<String, Object>) subMap));
                        }
                    }
                    criteriaList.add(new Criteria().orOperator(subCriterias.toArray(new Criteria[0])));
                } else {
                    Criteria campo = parseCampo(key, value);
                    if (campo != null) {
                        criteriaList.add(campo);
                    }
                }
            } catch (ParseException e) {
                System.err.println("❌ Error al parsear el filtro '" + key + "': " + e.getMessage());
            }
        }

        return criteriaList.isEmpty()
                ? new Criteria()
                : new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
    }

    /**
     * Construye un criterio individual según el tipo de valor.
     */
    private static Criteria parseCampo(String campo, Object valor) throws ParseException {
        if (valor == null)
            return null;

        if (valor instanceof String strValor && !strValor.isEmpty()) {
            return Criteria.where(campo).regex(strValor, "i");
        } else if (valor instanceof Number num) {
            return Criteria.where(campo).is(num);
        } else if (valor instanceof Boolean bool) {
            return Criteria.where(campo).is(bool);
        } else if (valor instanceof Date date) {
            return Criteria.where(campo).is(date);
        } else if (valor instanceof Map<?, ?> rango) {
            return parseRango(campo, rango);
        }

        return null; // no aplica
    }

    /**
     * Crea criterios de rango (fechas o números).
     */
    @SuppressWarnings("null")
    private static Criteria parseRango(String campo, Map<?, ?> rango) throws ParseException {
        Object inicio = rango.get("inicio");
        Object fin = rango.get("fin");
        Object min = rango.get("min");
        Object max = rango.get("max");

        // Rango de fechas
        if (inicio != null || fin != null) {
            Date fechaInicio = inicio != null ? dateFormat.parse(inicio.toString()) : null;
            Date fechaFin = fin != null ? dateFormat.parse(fin.toString()) : null;

            if (fechaInicio != null && fechaFin != null) {
                return Criteria.where(campo).gte(fechaInicio).lte(fechaFin);
            } else if (fechaInicio != null) {
                return Criteria.where(campo).gte(fechaInicio);
            } else {
                return Criteria.where(campo).lte(fechaFin);
            }
        }

        // Rango de números
        if (min != null || max != null) {
            try {
                Double minVal = min != null ? Double.parseDouble(min.toString()) : null;
                Double maxVal = max != null ? Double.parseDouble(max.toString()) : null;

                if (minVal != null && maxVal != null) {
                    return Criteria.where(campo).gte(minVal).lte(maxVal);
                } else if (minVal != null) {
                    return Criteria.where(campo).gte(minVal);
                } else {
                    return Criteria.where(campo).lte(maxVal);
                }
            } catch (NumberFormatException e) {
                System.err.println("⚠️ Valor de rango no numérico en campo: " + campo);
            }
        }

        return null;
    }
}
