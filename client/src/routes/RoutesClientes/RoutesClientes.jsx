import ClienteLayout from '../../layouts/ClienteLayout/ClienteLayout.jsx';
import { Home } from '../../views/Paneles/PanelesClientes/VistasClientes.jsx';

const rawRoutes = [{ path: '/', element: <Home /> }];

export const clientesRoutes = rawRoutes.map((route) => ({
	...route,
	layout: ClienteLayout,
}));
