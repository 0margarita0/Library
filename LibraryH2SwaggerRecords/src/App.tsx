//import { BrowserRouter, Routes, Route } from "react-router-dom";
import BooksPage from "./pages/BooksPage";
import AuthorsPage from "./pages/AuthorsPage";
import CountriesPage from "./pages/CountriesPage";
import StatisticsPage from "./pages/StatisticsPage";
import {ReactNode} from "react";

function BrowserRouter(props: { children: ReactNode }) {
    return null;
}

function Routes(props: { children: React.ReactNode }) {
    return null;
}

function Route(props: { path: string, element: React.JSX.Element }) {
    return null;
}

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<h1>Home</h1>} />
                <Route path="/books" element={<BooksPage />} />
                <Route path="/authors" element={<AuthorsPage />} />
                <Route path="/countries" element={<CountriesPage />} />
                <Route path="/statistics" element={<StatisticsPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;

<Route path="/statistics" element={<StatisticsPage />} />