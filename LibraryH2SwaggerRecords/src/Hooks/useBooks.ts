import { useState } from "react";
import axios from "../Axios/api";

export const useBooks = () => {
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(false);

    const fetchBooks = async () => {
        try {
            setLoading(true);
            const response = await axios.get("http://localhost:8080/api/books");
            setBooks(response.data);
        } catch (error) {
            console.error("Error fetching books", error);
        } finally {
            setLoading(false);
        }
    };

    return {
        books,
        loading,
        fetchBooks,
    };
};