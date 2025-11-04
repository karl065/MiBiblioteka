import { useDispatch, useSelector } from 'react-redux';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { GoMoveToStart } from 'react-icons/go';
import { logout } from '../../redux/shared/actions/logoutAppAction';
import { useState } from 'react';
import Login from '../Formularios/Login/Login';
import Modal from 'react-modal';

const NavBar = () => {
	const login = useSelector((state) => state.adminLogin.login);

	const [modalLogin, setModalLogin] = useState(false);

	const location = useLocation();

	console.log(location);
	console.log(login);

	const dispatch = useDispatch();
	const navigate = useNavigate();

	const handleLogOut = (e) => {
		e.preventDefault();
		logout(dispatch, navigate, login._id);
	};

	const handleCloseModalLogin = () => {
		setModalLogin(false);
	};

	return (
		<div className="flex w-[310px] lg:w-auto h-auto items-center justify-center">
			<div className="flex justify-center w-full p-2 bg-black rounded-lg opacity-70">
				<div className="w-full bg-white rounded-lg shadow dark:border dark:bg-gray-800 dark:border-gray-700">
					<div className="flex justify-center p-2 border-2 border-black rounded-lg">
						<div>
							<Modal
								ariaHideApp={false}
								isOpen={modalLogin}
								onRequestClose={handleCloseModalLogin}
								overlayClassName="fixed inset-0 bg-gray-500 bg-opacity-75 flex justify-center items-center"
								className="p-4 space-y-2 bg-white rounded-lg"
								style={{ overlay: { overflowY: 'scroll' } }}>
								<div className="flex justify-between p-2 border rounded-lg bg-secondary-fondo">
									<h1 className="font-bold text-white uppercase">
										Crear Usuario
									</h1>
									<button
										onClick={handleCloseModalLogin}
										className="transition duration-300 ease-in-out border rounded-md hover:bg-gray-600">
										❌
									</button>
								</div>
								<Login setModalLogin={setModalLogin} />
							</Modal>
						</div>
						{location.pathname !== '/' && (
							<div className="flex justify-end flex-1 p-2 space-x-2 ">
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
