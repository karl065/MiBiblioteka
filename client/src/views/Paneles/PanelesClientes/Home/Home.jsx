const Home = () => {
	return (
		<div className="h-full w-full ">
			<nav className="h-full p-2 bg-black rounded-lg opacity-70">
				<div className="h-full bg-white rounded-lg shadow dark:border dark:bg-gray-800 dark:border-gray-700">
					<div className="h-full flex justify-center p-2 border-2 border-black rounded-lg">
						<div className="p-2 border-2 border-pink-700 w-full h-full">
							<div className="flex items-center justify-center border-2 border-pink-700 font-bold text-white">
								<span>Home</span>
							</div>
							<hr className="my-2 border-2" />
						</div>
					</div>
				</div>
			</nav>
		</div>
	);
};

export default Home;
