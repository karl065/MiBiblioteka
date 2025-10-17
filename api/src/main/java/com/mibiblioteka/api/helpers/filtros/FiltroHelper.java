package com.mibiblioteka.api.helpers.filtros;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FiltroHelper {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Query construirQuery(Map<String, Object> filtros) {
        Query query = new Query();
        query.addCriteria(parseFiltros(filtros));
        return query;
    }

    @SuppressWarnings("unchecked")
    private static Criteria parseFiltros(Map<String, Object> filtros) {
        List<Criteria> criteriaList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filtros.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            try {
                if ("AND".equalsIgnoreCase(key) && value instanceof Map<?, ?>[] andArray) {
                    List<Criteria> subCriterias = new ArrayList<>();
                    for (Map<?, ?> subMap : andArray) {
                        subCriterias.add(parseFiltros((Map<String, Object>) subMap));
                    }
                    criteriaList.add(new Criteria().andOperator(subCriterias.toArray(new Criteria[0])));
                } else if ("OR".equalsIgnoreCase(key) && value instanceof Map<?, ?>[] orArray) {
                    List<Criteria> subCriterias = new ArrayList<>();
                    for (Map<?, ?> subMap : orArray) {
                        subCriterias.add(parseFiltros((Map<String, Object>) subMap));
                    }
                    criteriaList.add(new Criteria().orOperator(subCriterias.toArray(new Criteria[0])));
                } else {
                    criteriaList.add(parseCampo(key, value));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
    }

    @SuppressWarnings("null")
    private static Criteria parseCampo(String campo, Object valor) throws ParseException {
        if (valor instanceof String strValor && !strValor.isEmpty()) {
            return Criteria.where(campo).regex(strValor, "i");
        } else if (valor instanceof Number num) {
            return Criteria.where(campo).is(num);
        } else if (valor instanceof Boolean bool) {
            return Criteria.where(campo).is(bool);
        } else if (valor instanceof Date date) {
            return Criteria.where(campo).is(date);
        } else if (valor instanceof Map<?, ?> rango) {
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
            else if (min != null || max != null) {
                if (min != null && max != null) {
                    return Criteria.where(campo).gte(Double.valueOf(min.toString()))
                                                .lte(Double.valueOf(max.toString()));
                } else if (min != null) {
                    return Criteria.where(campo).gte(Double.valueOf(min.toString()));
                } else {
                    return Criteria.where(campo).lte(Double.valueOf(max.toString()));
                }
            }
        }
        return new Criteria(); // vacío si no aplica
    }
}
