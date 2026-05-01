import { useState } from "react";
import axios from "../Axios/api";

export const useCountries = () => {
    const [countries, setCountries] = useState([]);

    const fetchCountries = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/countries");
            setCountries(response.data);
        } catch (error) {
            console.error("Error fetching countries", error);
        }
    };

    return {
        countries,
        fetchCountries,
    };
};