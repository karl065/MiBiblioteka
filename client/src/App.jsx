/* eslint-disable react-hooks/exhaustive-deps */
import './App.css';
import { Route, Routes, useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { adminReloginAction } from './redux/admin/actions/adminReloginAction';
import { allRoutes } from './routes/routes';
import { adminLogoutAction } from './redux/admin/actions/adminLogoutAction';
import { Home } from './views/Paneles/PanelesClientes/VistasClientes';

function App() {
	const dispatch = useDispatch();
	const navigate = useNavigate();
	const login = useSelector((state) => state.adminLogin.login);

	const token = localStorage.getItem('token');

	const routesToRender = allRoutes[login.role] || [];

	useEffect(() => {
		if (!token) {
			adminLogoutAction(dispatch, navigate);
		} else {
			adminReloginAction(token, dispatch, navigate);
		}
	}, []);

	return (
		<Routes>
			<Route path="/" element={<Home />} />

			{routesToRender.map(({ path, element, layout: Layout }, i) => (
				<Route
					key={i}
					path={path}
					element={Layout ? <Layout>{element}</Layout> : element}
				/>
			))}
		</Routes>
	);
}

export default App;
