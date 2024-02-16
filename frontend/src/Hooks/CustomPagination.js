import React from 'react';
import Pagination from 'react-bootstrap/Pagination';

const CustomPagination = ({ totalPages, onPageChange, currentPage }) => {
  const maxButtonsToShow = 10; // Adjust as needed
  let startPage = Math.max(currentPage - Math.floor(maxButtonsToShow / 2), 1);
  let endPage = Math.min(startPage + maxButtonsToShow - 1, totalPages);

  if (endPage === totalPages) {
    startPage = Math.max(endPage - maxButtonsToShow + 1, 1);
  }

  let paginationItems = [];

  // Ellipsis for skipping to previous range if not at the start
  if (startPage > 1) {
    paginationItems.push(
      <Pagination.Ellipsis
        key="prev-ellipsis"
        onClick={() => onPageChange(startPage - 1)}
      />
    );
  }

  for (let number = startPage; number <= endPage; number++) {
    paginationItems.push(
      <Pagination.Item
        key={number}
        active={number === currentPage}
        onClick={() => onPageChange(number)}
      >
        {number}
      </Pagination.Item>
    );
  }

  // Ellipsis for skipping to next range if not at the end
  if (endPage < totalPages) {
    paginationItems.push(
      <Pagination.Ellipsis
        key="next-ellipsis"
        onClick={() => onPageChange(endPage + 1)}
      />
    );
  }

  return (
    <Pagination className="justify-content-center mt-3">
      {paginationItems}
    </Pagination>
  );
};

export default CustomPagination;
