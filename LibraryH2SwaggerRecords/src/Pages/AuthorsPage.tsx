import { useAuthors } from "../hooks/useAuthors";
import { useEffect } from "react";

function AuthorsPage() {
    const { authors, fetchAuthors } = useAuthors();

    useEffect(() => {
        fetchAuthors();
    }, []);

    return (
        <div>
            <h2>Authors</h2>
            <ul>
                {authors.map((author: any) => (
                    <li key={author.id}>
                        {author.name} {author.surname}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default AuthorsPage;