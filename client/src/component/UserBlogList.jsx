import axios from "axios";
import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";

export default function UserBlogList() {
  const [blogList, setBlogList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const blogsPerPage = 10;

  useEffect(() => {
    axios
      .get("http://localhost:8080/blogs/all-blog")
      .then((response) => {
        setBlogList(response.data);
        console.log(response.data[0]);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  }, []);

  // Calculate total pages
  const totalPages = Math.ceil(blogList.length / blogsPerPage);

  // Get current users
  const indexOfLastBlog = currentPage * blogsPerPage;
  const indexOfFirstBlog = indexOfLastBlog - blogsPerPage;
  const currentBlogs = blogList.slice(indexOfFirstBlog, indexOfLastBlog);

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
              <th scope="col">Blog ID</th>
              <th scope="col">Blog Post</th>
              <th scope="col">Comment</th>
            </tr>
          </thead>
          <tbody>
            {currentBlogs.map((blog) => (
              <tr key={blog.blogId}>
                <th scope="row">{blog.blogId}</th>
                <td>{blog.blogPost}</td>
                <td>{blog.comment}</td>
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
