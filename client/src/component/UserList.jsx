import axios from "axios";
import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
export default function UserList() {
  const [userList, setUserList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const usersPerPage = 10;

  useEffect(() => {
    axios
      .get("http://localhost:8080/users/all-users")
      .then((response) => {
        setUserList(response.data);
        console.log(response.data[0]);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }, []);

  // Calculate total pages
  const totalPages = Math.ceil(userList.length / usersPerPage);

  // Get current users
  const indexOfLastUser = currentPage * usersPerPage;
  const indexOfFirstUser = indexOfLastUser - usersPerPage;
  const currentUsers = userList.slice(indexOfFirstUser, indexOfLastUser);

  // Handle page change
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <>
      <NavBar />
      <div className="content " style={{ margin: "7%" }}>
        <h3>User List</h3>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">User ID</th>
              <th scope="col">Username</th>
              <th scope="col">Email</th>
              <th scope="col">Age</th>
            </tr>
          </thead>
          <tbody>
            {currentUsers.map((user) => (
              <tr key={user.userId}>
                <th scope="row">{user.userId}</th>
                <td>{user.username}</td>
                <td>{user.email}</td>
                <td>{user.age}</td>
              </tr>
            ))}
          </tbody>
        </table>
        {/* Pagination */}
        <nav>
          <ul className="pagination">
            {Array.from({ length: totalPages }, (_, index) => (
              <li
                className={`page-item ${
                  currentPage === index + 1 ? "active" : ""
                }`}
                key={index + 1}
              >
                <button
                  className="page-link"
                  onClick={() => handlePageChange(index + 1)}
                >
                  {index + 1}
                </button>
              </li>
            ))}
          </ul>
        </nav>
      </div>
    </>
  );
}
