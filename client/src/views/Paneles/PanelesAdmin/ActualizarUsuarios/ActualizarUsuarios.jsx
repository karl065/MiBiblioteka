/* eslint-disable react-hooks/exhaustive-deps */
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import { alertSuccess } from '../../../../helpers/Alertas';
import { paramsLocations } from '../../../../helpers/Params';
import { actualizarUsuariosAction } from '../../../../redux/admin/actions/usuariosActions/ActualizarUsuarioAction';
import Asignaciones from '../../../../components/Asignaciones/Asignaciones';

const ActualizarUsuarios = () => {
	const doc = paramsLocations('doc');
	const usuarios = useSelector((state) => state.usuarios.usuarios);
	const [correoBuscado, setCorreoBuscado] = useState('');
	const role = useSelector((state) => state.roles.roles);
	const [idUser, setIdUser] = useState('');
	const dispatch = useDispatch();

	const [usuario, setUsuario] = useState({
		nombre: '',
		correo: '',
		celular: '',
		password: '',
		role: '',
		librosPrestados: [],
		librosLeidos: [],
	});

	const handleSetUser = () => {
		const usuarioBuscado = usuarios.find(
			(usuario) => usuario.correo === correoBuscado
		);
		if (usuarioBuscado) {
			setIdUser(usuarioBuscado._id);
			const usuarioFiltrado = {
				nombre: usuarioBuscado?.nombre || '',
				correo: usuarioBuscado?.correo || '',
				celular: usuarioBuscado?.celular || '',
				password: usuarioBuscado?.password || '',
				role: usuarioBuscado?.role || '',
			};

			setUsuario({ ...usuarioFiltrado });

			formik.setValues({ ...usuarioFiltrado });
		}
	};

	const validationSchema = Yup.object({
		nombre: Yup.string().required('Campo obligatorio'),
		correo: Yup.string().required('Campo obligatorio'),
		celular: Yup.string().required('Campo obligatorio'),
		role: Yup.string().required('Campo obligatorio'),
	});

	const formik = useFormik({
		initialValues: usuario,
		validationSchema: validationSchema,
		onSubmit: async (values) => {
			if (!values.password) {
				const dataUpdate = {
					updateUser: {
						nombre: values.nombre,
						correo: values.correo,
						celular: values.celular,
						role: values.role,
					},
				};
				await actualizarUsuariosAction(idUser, dataUpdate, dispatch);
			} else {
				const dataUpdate = {
					updateUser: values,
				};
				await actualizarUsuariosAction(idUser, dataUpdate, dispatch);
			}
			alertSuccess('Actualizado con éxito');
		},
	});

	useEffect(() => {
		if (doc) {
			setCorreoBuscado(doc);
		}
		if (correoBuscado) handleSetUser();
	}, [doc, correoBuscado]);

	return (
		<div className="flex">
			<div className="w-full p-5 space-y-5 overflow-y-auto bg-black rounded-lg opacity-70">
				<div className="bg-white rounded-lg shadow  dark:border dark:bg-gray-800 dark:border-gray-700">
					<div className="border-2 border-black rounded-lg md:space-y-6 sm:p-8">
						<h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
							Actualizar Usuario
						</h1>
						<div className="flex space-x-2">
							<input
								className="bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
								type="text"
								name="correoBuscado"
								id="correoBuscado"
								value={correoBuscado}
								onChange={(e) => setCorreoBuscado(e.target.value)}
								placeholder="Buscar Correo"
							/>
						</div>
						<form className="space-x-2" onSubmit={formik.handleSubmit}>
							<div className="flex">
								<div className="items-center justify-center p-2 space-y-2">
									<div>
										<input
											type="text"
											name="correo"
											id="correo"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.correo}
											className={`bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.correo && formik.errors.correo
													? 'border-red-500'
													: ''
											}`}
											placeholder="Correo"
										/>
										{formik.touched.correo && formik.errors.correo ? (
											<div className="text-xs text-red-500">
												{formik.errors.correo}
											</div>
										) : null}
									</div>
									<div>
										<input
											type="text"
											name="nombre"
											id="nombre"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.nombre}
											className={`bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.nombre && formik.errors.nombre
													? 'border-red-500'
													: ''
											}`}
											placeholder="Primer Nombre"
										/>
										{formik.touched.nombre && formik.errors.nombre ? (
											<div className="text-xs text-red-500">
												{formik.errors.nombre}
											</div>
										) : null}
									</div>
								</div>
								<div className="items-center justify-center p-2 space-y-2">
									<div>
										<input
											type="text"
											name="segundoNombre"
											id="segundoNombre"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.segundoNombre}
											className={`bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.segundoNombre &&
												formik.errors.segundoNombre
													? 'border-red-500'
													: ''
											}`}
											placeholder="Segundo Nombre"
										/>
										{formik.touched.segundoNombre &&
										formik.errors.segundoNombre ? (
											<div className="text-xs text-red-500">
												{formik.errors.segundoNombre}
											</div>
										) : null}
									</div>
									<div>
										<input
											type="text"
											name="primerApellido"
											id="primerApellido"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.primerApellido}
											className={`bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.primerApellido &&
												formik.errors.primerApellido
													? 'border-red-500'
													: ''
											}`}
											placeholder="Primer Apellido"
										/>
										{formik.touched.primerApellido &&
										formik.errors.primerApellido ? (
											<div className="text-xs text-red-500">
												{formik.errors.primerApellido}
											</div>
										) : null}
									</div>
								</div>
								<div className="items-center justify-center p-2 space-y-2">
									<div>
										<input
											type="text"
											name="segundoApellido"
											id="segundoApellido"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.segundoApellido}
											className={`bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.segundoApellido &&
												formik.errors.segundoApellido
													? 'border-red-500'
													: ''
											}`}
											placeholder="Segundo Apellido"
										/>
										{formik.touched.segundoApellido &&
										formik.errors.segundoApellido ? (
											<div className="text-xs text-red-500">
												{formik.errors.segundoApellido}
											</div>
										) : null}
									</div>
									<div>
										<input
											type="text"
											name="correo"
											id="correo"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.correo}
											className={`bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.correo && formik.errors.correo
													? 'border-red-500'
													: ''
											}`}
											placeholder="Correo"
										/>
										{formik.touched.correo && formik.errors.correo ? (
											<div className="text-xs text-red-500">
												{formik.errors.correo}
											</div>
										) : null}
									</div>
								</div>
								<div className="items-center justify-center p-2 space-y-2">
									<div>
										<input
											type="text"
											name="celular"
											id="celular"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.celular}
											className={`bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.celular && formik.errors.celular
													? 'border-red-500'
													: ''
											}`}
											placeholder="Celular"
										/>
										{formik.touched.celular && formik.errors.celular ? (
											<div className="text-xs text-red-500">
												{formik.errors.celular}
											</div>
										) : null}
									</div>
									<div>
										<input
											type="password"
											name="password"
											id="password"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.password}
											placeholder="Contraseña Nueva"
											className={`bg-gray-50 uppercase border-4 border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.password && formik.errors.password
													? 'border-red-500'
													: ''
											}`}
										/>
										{formik.touched.password && formik.errors.password ? (
											<div className="text-xs text-red-500">
												{formik.errors.password}
											</div>
										) : null}
									</div>
								</div>
								<div className="items-center justify-center p-2 space-y-2">
									<div className="flex-1">
										<select
											name="role"
											id="role"
											onChange={formik.handleChange}
											onBlur={formik.handleBlur}
											value={formik.values.role}
											className={`bg-blue-700 uppercase border-4 border-black text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-black dark:placeholder-white dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ${
												formik.touched.role && formik.errors.role
													? 'border-red-500'
													: ''
											}`}>
											<option value="">Seleccionar un Rol</option>
											{role.slice(1).map((rol, index) => (
												<option value={rol} key={index}>
													{rol}
												</option>
											))}
										</select>
										{formik.touched.role && formik.errors.role && (
											<div className="text-xs text-red-500">
												{formik.errors.role}
											</div>
										)}
									</div>
									<div>
										<button
											type="submit"
											className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">
											Actualizar
										</button>
									</div>
								</div>
							</div>
						</form>
						<Asignaciones idUser={idUser} />
					</div>
				</div>
			</div>
		</div>
	);
};

export default ActualizarUsuarios;
