import { renderHook } from '@testing-library/react-hooks';
import UseShelters from './UseShelters'; // Adjust the import path as necessary
import { getShelters } from '../../Services/shelterApi/shelterApi';

// Mock the shelterApi module
jest.mock('../../Services/shelterApi/shelterApi');

describe('UseShelters custom hook', () => {
  // Setup for localStorage mock
  beforeEach(() => {
    localStorage.clear();
    jest.resetAllMocks();
  });

  test('initially returns an empty array', async () => {
    const { result, waitForNextUpdate } = renderHook(() => UseShelters());

    // Assert the initial state of the hook
    expect(result.current).toEqual([]);

    // Wait for any state updates if they occur
    await waitForNextUpdate();
  });

  test('fetches shelters and updates state', async () => {
    const mockShelters = [
      { id: 1, name: 'Shelter 1' },
      { id: 2, name: 'Shelter 2' },
    ];
    getShelters.mockResolvedValue(mockShelters);

    const { result, waitForNextUpdate } = renderHook(() => UseShelters());

    // Wait for the hook to update state after the async operation
    await waitForNextUpdate();

    // Assert the state after fetching shelters
    expect(result.current).toEqual(mockShelters);
  });

  test('fetches shelters from localStorage if available', async () => {
    const mockShelters = [
      { id: 1, name: 'Shelter 1' },
      { id: 2, name: 'Shelter 2' },
    ];
    localStorage.setItem('sheltersData', JSON.stringify(mockShelters));

    const { result } = renderHook(() => UseShelters());

    // Assert the state directly from localStorage, no need to wait for updates
    expect(result.current).toEqual(mockShelters);
  });
});
