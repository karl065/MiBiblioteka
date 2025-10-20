import React from 'react';

const Home = () => {
	return (
		<div>
			<nav className="p-2 bg-black rounded-lg opacity-70">
				<div className="bg-white rounded-lg shadow dark:border dark:bg-gray-800 dark:border-gray-700">
					<div className="flex justify-center p-2 border-2 border-black rounded-lg">
						<div className="p-2">
							<div className="font-bold text-white">
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
