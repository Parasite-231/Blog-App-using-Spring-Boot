import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import UserBlogList from "./page/UserBlogList";
import UserListPage from "./page/UserListPage";


function App() {
  return (
    <Router>
    <Routes>
      {/* //Platform selection  Pages */}
      <Route
        path="/"
        element={<UserBlogList />}
      />
      <Route
        path="/user-list"
        element={<UserListPage />}
      />

      </Routes>
      </Router>
  );
}

export default App;
