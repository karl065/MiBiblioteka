import axios from 'axios';
import { setLogin } from '../slices/loginSlice';
import { cargarUsuarios } from '../slices/usuariosSlice';
import server from '../../../conexiones/conexiones';

export const adminLogoutAction = async (dispatch, navigate, idUser) => {
	try {
		if (idUser) {
			const dataUpdate = {
				updateUser: {
					activo: false,
				},
			};
			await axios.put(
				`${server.api.baseURL}usuarios/actualizar/${idUser}`,
				dataUpdate
			);
		}
		dispatch(setLogin([]));
		dispatch(cargarUsuarios([]));
	} catch (error) {
		console.log(error);
	}
};
