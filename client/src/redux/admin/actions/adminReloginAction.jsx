import axios from 'axios';
import { alertInfo, alertSuccess } from '../../../helpers/Alertas';
import { isTokenExpired } from '../../../helpers/Verificacion';
import { setLogin } from '../slices/loginSlice';
import server from '../../../conexiones/conexiones';
import { cargarUsuariosAction } from './usuariosActions/cargarUsuariosAction';

export const adminReloginAction = async (token, dispatch, navigate) => {
	try {
		if (token) {
			const expirado = isTokenExpired(token, dispatch, navigate);
			if (!expirado) {
				const { data } = await axios.get(`${server.api.baseURL}auth`, {
					headers: {
						'x-auth-token': token,
					},
				});

				if (data) {
					dispatch(setLogin(data));
					cargarUsuariosAction(dispatch);
				}
				if (data.role === 'View') {
					navigate('/view');
				} else {
					navigate('/admin');
				}
				alertSuccess(`Bienvenido de nuevo ${data.primerNombre}`);
			}
		}
	} catch (error) {
		console.log(error);
		const { msg } = error.response.data;
		if (msg === 'Token no valido') {
			localStorage.removeItem('token');
			alertInfo('Tu sesión ha expirado, por favor inicia sesión nuevamente.');
			navigate('/');
		} else if (msg === 'No hay token') navigate('/');
	}
};
