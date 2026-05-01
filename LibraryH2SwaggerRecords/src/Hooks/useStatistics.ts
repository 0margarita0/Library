import { useEffect, useState } from "react";
import axios from "../Axios/api";

export const useStatistics = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        axios
            .get("http://localhost:8080/api/books/statistics")
            .then((res) => setData(res.data))
            .catch((err) => console.log(err));
    }, []);

    return data;
};