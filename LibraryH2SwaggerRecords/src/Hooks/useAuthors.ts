import { useState } from "react";
import axios from "../Axios/api";

export const useAuthors = () => {
    const [authors, setAuthors] = useState([]);

    const fetchAuthors = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/authors");
            setAuthors(response.data);
        } catch (error) {
            console.error("Error fetching authors", error);
        }
    };

    return {
        authors,
        fetchAuthors,
    };
};