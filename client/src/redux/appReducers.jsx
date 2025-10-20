import { combineReducers } from '@reduxjs/toolkit';

// App Slices

import loadingReducer from './app/slices/loadingSlice.jsx';

// Admin Slices
import adminLoginReducer from './admin/slices/loginSlice.jsx';
import usuariosReducer from './admin/slices/usuariosSlice.jsx';
import rolesReducer from './admin/slices/rolesSlice.jsx';

// Shared Slices

const appReducer = combineReducers({
	// App Reducers
	loading: loadingReducer,

	// Admin Reducers
	adminLogin: adminLoginReducer,
	usuarios: usuariosReducer,
	roles: rolesReducer,

	// Shared Reducers
});

export default appReducer;
