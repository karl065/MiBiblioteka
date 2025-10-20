import AdminLayout from '../../layouts/AdminLayout/AdminLayout.jsx';
import { Home } from '../../views/Paneles/PanelesClientes/VistasClientes.jsx';

const rawRoutes = [{ path: '/', element: <Home /> }];

export const routesShared = rawRoutes.map((route) => ({
	...route,
	layout: AdminLayout,
}));
