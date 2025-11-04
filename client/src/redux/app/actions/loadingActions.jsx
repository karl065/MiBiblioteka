export const setLoading = (estado, dispatch) => {
	try {
		dispatch(setLoading(estado));
	} catch (error) {
		console.log(error.message);
	}
};
