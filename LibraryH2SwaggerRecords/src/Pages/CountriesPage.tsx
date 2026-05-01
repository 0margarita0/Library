import { useCountries } from "../hooks/useCountries";
import { useEffect } from "react";

function CountriesPage() {
    const { countries, fetchCountries } = useCountries();

    useEffect(() => {
        fetchCountries();
    }, []);

    return (
        <div>
            <h2>Countries</h2>
            <ul>
                {countries.map((c: any) => (
                    <li key={c.id}>
                        {c.name} ({c.continent})
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default CountriesPage;