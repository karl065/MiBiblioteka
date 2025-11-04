import axios from 'axios';
import { alertSuccess, alertWarning } from '../../../helpers/Alertas';
import server from '../../../conexiones/conexiones';
import { setLogin } from '../slices/loginSlice';
import { setLoading } from '../../app/actions/loadingActions';

export const adminLoginAction = async (userLogin, dispatch, navigate) => {
	try {
		const { data } = await axios.post(`${server.api.baseURL}auth`, userLogin);

		if (data) {
			localStorage.setItem('token', data.token);
			localStorage.setItem('connect', data.connectedDB);
			dispatch(setLogin(data));

			data.role === 'View' ? navigate('/view') : navigate('/admin');
			setLoading(false, dispatch);

			alertSuccess(`Bienvenido ${data.primerNombre}`);
		}
	} catch (error) {
		const { data } = error;
		alertWarning(data);
		setLoading(false, dispatch);
	}
};
