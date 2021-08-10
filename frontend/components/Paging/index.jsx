import React, { useState, useEffect } from 'react';
import '@components/Paging/styles.css';
import Pagination from 'react-js-pagination';

const Paging = ({ bookBoard = null, discussion = null, totalElements, currentPage, handlePageChange }) => {
  return (
    <div>
      {totalElements &&
        (bookBoard ? (
          <Pagination
            activePage={currentPage + 1}
            itemsCountPerPage={8}
            totalItemsCount={totalElements}
            pageRangeDisplayed={5}
            prevPageText={'<'}
            nextPageText={'>'}
            onChange={handlePageChange}
          />
        ) : discussion ? (
          <Pagination
            activePage={currentPage + 1}
            itemsCountPerPage={7}
            totalItemsCount={totalElements}
            pageRangeDisplayed={10}
            prevPageText={'<'}
            nextPageText={'>'}
            onChange={handlePageChange}
          />
        ) : (
          <Pagination
            activePage={currentPage + 1}
            itemsCountPerPage={20}
            totalItemsCount={totalElements}
            pageRangeDisplayed={10}
            prevPageText={'<'}
            nextPageText={'>'}
            onChange={handlePageChange}
          />
        ))}
    </div>
  );
};

export default Paging;
