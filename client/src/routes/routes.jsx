import { adminRoutes } from './RoutesAdmin/RoutesAdmin.jsx';
import { clientesRoutes } from './RoutesClientes/RoutesClientes.jsx';

export const allRoutes = {
	Admin: adminRoutes,
	SuperAdmin: adminRoutes,
	Cliente: clientesRoutes,
};
