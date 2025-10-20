import { adminRoutes } from './RoutesAdmin/RoutesAdmin.jsx';
import { clientesRoutes } from './RoutesClientes/RoutesClientes.jsx';
import { routesShared } from './RoutesShared/RoutesShared.jsx';

export const allRoutes = {
	Admin: [...routesShared, ...adminRoutes],
	SuperAdmin: [...routesShared, ...adminRoutes],
	Cliente: [...routesShared, ...clientesRoutes],
	Shared: routesShared,
};
