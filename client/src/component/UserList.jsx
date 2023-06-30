import axios from "axios";
import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
export default function UserList() {
  const [userList, setUserList] = useState([]);
  const [username, setUsername] = useState("");
  const [selectedUserId, setSelectedUserId] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [successMessage, setSuccessMessage] = useState(false);
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

  const deleteUser = (userId) => {
    axios
      .delete(`http://localhost:8080/users/delete-user/${userId}`)
      .then((response) => {
        // Remove the deleted user from the user list
        const updatedUserList = userList.filter(
          (user) => user.userId !== userId
        );
        setUserList(updatedUserList);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };
  const editUser = () => {
    if (selectedUserId) {
      axios
        .put(`http://localhost:8080/users/edit-user/${selectedUserId}`, {
          username,
        })
        .then((response) => {
          // Handle the response as needed
          console.log("User updated successfully");
          window.location.reload();
          setSuccessMessage(true);
          setTimeout(() => {
            setSuccessMessage(false);
          }, 7000);
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    }
  };

  const handleEditClick = (userId) => {
    setSelectedUserId(userId);
  };

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
      {successMessage && (
        <div class="alert alert-success" role="alert">
          This is a success alert—check it out!
        </div>
      )}
      <div className="content " style={{ margin: "7%" }}>
        <h3>User List</h3>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">User ID</th>
              <th scope="col">Username</th>
              <th scope="col">Email</th>
              <th scope="col">Age</th>
              <th scope="col">Remove</th>
              <th scope="col">Edit</th>
            </tr>
          </thead>
          <tbody>
            {currentUsers.map((user) => (
              <tr key={user.userId}>
                <th scope="row">{user.userId}</th>
                <td>{user.username}</td>
                <td>{user.email}</td>
                <td>{user.age}</td>
                <td>
                  <button
                    type="button"
                    onClick={() => deleteUser(user.userId)}
                    className="btn btn-danger"
                  >
                    Delete
                  </button>
                </td>
                <td>
                  <button
                    type="button"
                    className="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#staticBackdrop"
                    onClick={() => handleEditClick(user.userId)} // Pass the userId to the handler
                  >
                    Edit
                  </button>
                </td>
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

      <div
        class="modal fade"
        id="staticBackdrop"
        data-bs-backdrop="static"
        data-bs-keyboard="false"
        tabindex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="staticBackdropLabel">
                Edit User
              </h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form method="POST">
                <div className="mb-3">
                  <label
                    htmlFor="exampleFormControlInput1"
                    className="form-label"
                  >
                    Username
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="exampleFormControlInput1"
                    placeholder="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                  />
                </div>
              </form>
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button
                type="button"
                className="btn btn-primary"
                data-bs-dismiss="modal" // Add this attribute to close the modal
                onClick={() => editUser()}
              >
                Save Changes
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
