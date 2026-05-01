import { useStatistics } from "../Hooks/useStatistics";

const StatisticsPage = () => {
    const stats = useStatistics();

    return (
        <div>
            <h1>Statistics</h1>

            <table>
                <thead>
                <tr>
                    <th>Category</th>
                    <th>Total Books</th>
                </tr>
                </thead>
                <tbody>
                {stats.map((s: any, index: number) => (
                    <tr key={index}>
                        <td>{s.category}</td>
                        <td>{s.count}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default StatisticsPage;