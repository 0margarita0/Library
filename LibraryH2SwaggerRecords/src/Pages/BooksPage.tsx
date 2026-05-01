import { useBooks } from "../hooks/useBooks";
import { useEffect } from "react";

function BooksPage() {
    const { books, loading, fetchBooks } = useBooks();

    useEffect(() => {
        fetchBooks();
    }, []);

    if (loading) return <p>Loading...</p>;

    return (
        <div>
            <h2>Books</h2>
            <ul>
                {books.map((book: any) => (
                    <li key={book.id}>
                        {book.name} - {book.category} ({book.availableCopies})
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default BooksPage;