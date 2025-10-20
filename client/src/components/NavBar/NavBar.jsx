import { useDispatch, useSelector } from 'react-redux';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { GoMoveToStart } from 'react-icons/go';
import { logout } from '../../redux/shared/actions/logoutAppAction';

const NavBar = () => {
	const login = useSelector((state) => state.adminLogin.login);

	const location = useLocation();

	const dispatch = useDispatch();
	const navigate = useNavigate();

	const handleLogOut = (e) => {
		e.preventDefault();
		logout(dispatch, navigate, login._id);
	};

	return (
		<div className="flex w-[310px] lg:w-auto h-auto items-center justify-center">
			<div className="flex justify-center w-full p-2 bg-black rounded-lg opacity-70">
				<div className="w-full bg-white rounded-lg shadow dark:border dark:bg-gray-800 dark:border-gray-700">
					<div className="flex justify-center p-2 border-2 border-black rounded-lg">
						<div
							className={`${
								login.role === 'View'
									? 'flex flex-1 justify-end p-2'
									: 'flex flex-1 justify-center p-2'
							}`}>
							<h1 className="text-sm font-bold text-black uppercase dark:text-white lg:text-lg">{`Conjunto `}</h1>
						</div>
						{login.role === 'View' && (
							<div className="flex justify-end flex-1 p-2 space-x-2 ">
								{location.pathname !== '/view' && (
									<Link to="/view">
										<GoMoveToStart style={{ color: 'white' }} size={34} />
									</Link>
								)}
								<button
									onClick={handleLogOut}
									className="flex items-center justify-center w-8 h-8 text-white bg-red-500 rounded-full hover:bg-red-300 hover:text-gray-800 focus:outline-none focus:ring-2 focus:ring-gray-500">
									<svg
										xmlns="http://www.w3.org/2000/svg"
										className="w-6 h-6"
										fill="none"
										viewBox="0 0 24 24"
										stroke="currentColor">
										<path
											strokeLinecap="round"
											strokeLinejoin="round"
											strokeWidth="4"
											d="M6 18L18 6M6 6l12 12"
										/>
									</svg>
								</button>
							</div>
						)}
					</div>
				</div>
			</div>
		</div>
	);
};

export default NavBar;
