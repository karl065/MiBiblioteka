import { useLocation } from 'react-router-dom';
import NavBar from '../../components/NavBar/NavBar.jsx';
import SidebarUsuario from '../../components/Sidebar/SidebarUsuario.jsx';

const ClienteLayout = ({ children }) => {
	const location = useLocation();
	const isHome = location.pathname === '/';

	console.log(isHome);

	return (
		<div className="flex w-screen h-screen p-2 space-x-2">
			{isHome && <SidebarUsuario />}
			<div className="flex flex-col flex-1 space-y-2">
				<NavBar />
				<div className="flex-1 overflow-y-auto">{children}</div>
			</div>
		</div>
	);
};

export default ClienteLayout;
