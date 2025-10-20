import AdminLayout from '../../layouts/AdminLayout/AdminLayout.jsx';
import {
	ActualizarUsuarios,
	CrearUsuarios,
	IngresoAdmin,
} from '../../views/Paneles/PanelesAdmin/VistasAdmin.jsx';

const rawRoutes = [
	{ path: '/admin', element: <IngresoAdmin /> },
	{ path: '/CrearUsuario', element: <CrearUsuarios /> },
	{
		path: '/ActualizarUsuario',
		element: <ActualizarUsuarios />,
	},
];

export const adminRoutes = rawRoutes.map((route) => ({
	...route,
	layout: AdminLayout,
}));
