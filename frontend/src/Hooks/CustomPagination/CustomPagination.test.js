import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom';
import CustomPagination from './CustomPagination';

describe('CustomPagination Component', () => {
  const onPageChangeMock = jest.fn();

  beforeEach(() => {
    onPageChangeMock.mockClear();
  });

  test('renders correct number of pagination items', () => {
    render(
      <CustomPagination
        totalPages={12}
        currentPage={1}
        onPageChange={onPageChangeMock}
      />
    );
    // Total = 12, currentPage = 1, should render 10 items (1-10) + 1 ellipsis for next
    expect(screen.getAllByRole('button')).toHaveLength(11); // 10 page numbers + 1 next ellipsis
  });

  test('handles page change on clicking a page number', () => {
    render(
      <CustomPagination
        totalPages={12}
        currentPage={1}
        onPageChange={onPageChangeMock}
      />
    );
    const page2Button = screen.getByText('2');
    fireEvent.click(page2Button);
    expect(onPageChangeMock).toHaveBeenCalledWith(2);
  });

  test('renders ellipsis for previous and next range', () => {
    render(
      <CustomPagination
        totalPages={30}
        currentPage={15}
        onPageChange={onPageChangeMock}
      />
    );
    // With currentPage = 15 in a range of 30, both previous and next ellipses should be rendered
    const ellipses = screen.getAllByText('…');
    expect(ellipses).toHaveLength(2); // Previous and next
  });

  test('handles range change on clicking next range ellipsis', () => {
    render(
      <CustomPagination
        totalPages={30}
        currentPage={10}
        onPageChange={onPageChangeMock}
      />
    );
    const nextRangeEllipsis = screen.getAllByText('…')[0]; // Assuming next ellipsis is the first one
    fireEvent.click(nextRangeEllipsis);
    // Assuming maxButtonsToShow is 10, clicking next ellipsis from page 10 should go to page 11
    expect(onPageChangeMock).toHaveBeenCalledWith(11);
  });

  test('handles range change on clicking previous range ellipsis', () => {
    render(
      <CustomPagination
        totalPages={30}
        currentPage={20}
        onPageChange={onPageChangeMock}
      />
    );
    const prevRangeEllipsis = screen.getAllByText('…')[0]; // Assuming previous ellipsis is the first one
    fireEvent.click(prevRangeEllipsis);
    // Assuming maxButtonsToShow is 10, clicking previous ellipsis from page 20 should go to page 19
    expect(onPageChangeMock).toHaveBeenCalledWith(19);
  });

  // Add more tests as needed to cover other scenarios and edge cases
});
