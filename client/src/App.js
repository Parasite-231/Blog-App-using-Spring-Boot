import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import UserPostList from "./component/UserPostList";
import UserListPage from "./page/UserListPage";


function App() {
  return (
    <Router>
    <Routes>
      {/* //Platform selection  Pages */}
      <Route
        path="/"
        element={<UserPostList />}
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
