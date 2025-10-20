import {
	useReactTable,
	getCoreRowModel,
	flexRender,
} from '@tanstack/react-table';

const Tabla = ({ columns, data, className, firstRowClass }) => {
	const table = useReactTable({
		columns,
		data,
		getCoreRowModel: getCoreRowModel(),
	});

	return (
		<div className={`overflow-x-auto overflow-y-auto ${className}`}>
			<table className="w-full">
				<thead>
					{table.getHeaderGroups().map((headerGroup) => (
						<tr key={headerGroup.id}>
							{headerGroup.headers.map((header, index) => (
								<th
									key={header.id}
									className={`p-2 ${
										index === headerGroup.headers.length - 1
											? 'rounded-tr-lg'
											: ''
									} ${index === 0 ? 'rounded-tl-lg' : ''} ${
										index === headerGroup.headers.length - 1 ? '' : 'border-r'
									} font-bold text-left text-white bg-gray-600`}>
									{header.isPlaceholder
										? null
										: flexRender(
												header.column.columnDef.header,
												header.getContext()
										  )}
								</th>
							))}
						</tr>
					))}
				</thead>

				<tbody>
					{table.getRowModel().rows.map((row) => (
						<tr key={row.id}>
							{row.getVisibleCells().map((cell, index) => (
								<td
									key={cell.id}
									className={`${
										firstRowClass ? firstRowClass : 'text-white'
									} border-t-4 p-2 font-bold hover:bg-white hover:text-black ${
										index === row.getVisibleCells().length - 1 ? '' : 'border-r'
									}`}>
									{flexRender(cell.column.columnDef.cell, cell.getContext())}
								</td>
							))}
						</tr>
					))}
				</tbody>
			</table>
		</div>
	);
};

export default Tabla;
