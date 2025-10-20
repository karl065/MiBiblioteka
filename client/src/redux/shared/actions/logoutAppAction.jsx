import axios from 'axios';
import server from '../../../conexiones/conexiones.jsx';

export const logout = async (dispatch, navigate, idUser) => {
	try {
		if (idUser) {
			const dataUpdate = {
				updateUser: {
					activo: false,
				},
			};
			await axios.put(
				`${server.api.baseURL}/usuarios/actualizar/${idUser}`,
				dataUpdate
			);
		}

		localStorage.removeItem('token');

		dispatch({ type: 'RESET_ALL_STATE' });

		navigate && navigate('/');
	} catch (error) {
		console.log({ error: error });
	}
};
