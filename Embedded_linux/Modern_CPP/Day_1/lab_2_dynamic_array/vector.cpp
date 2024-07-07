#include "vector.hpp"

// Initialize the vector with a given size
ret_status vect_init(vector* vect, const unsigned int size)
{
    ret_status ret = NOK;

    if ((NULL == vect) || (size == 0)) {
        // Do nothing if the vector is NULL or size is 0
    } else {
        // Allocate memory for the vector's head with the given size
        vect->head = (int*)calloc(size, sizeof(int));
        vect->capacity = size; // Set the vector's capacity
        vect->size = 0; // Initialize the vector's size to 0
        ret = OK; // Set the status to OK
    }

    return ret; // Return the status
}

// Insert a value at a specific index in the vector
ret_status vect_insert_at(vector* vect, const int val, const unsigned int index)
{
    ret_status ret = NOK;

    if (NULL == vect) {
        // Do nothing if the vector is NULL
    } else {
        // Check if the vector needs to be reallocated
        if (vect->capacity == vect->size) {
            vect->head = (int*)realloc(vect->head, (vect->capacity * 2) * sizeof(int));
            vect->capacity *= 2; // Double the capacity
            ret = OK;
            cout << "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" << endl;
        } else {
            // Do nothing if reallocation is not needed
        }

        // Insert value if index is at or beyond the current size
        if (index >= vect->size) {
            *((vect->head) + index) = val;
            ++(vect->size);
            ret = OK;
        }

        // Insert value and shift elements if index is within the current size
        if (index < vect->size) {
            // Temporary array to hold current elements
            int* temp = (int*)malloc(sizeof(int) * vect->size);
            for (unsigned int i = 0; i < vect->size; ++i) {
                *(temp + i) = *((vect->head) + i);
            }

            // Insert the new value at the specified index
            *((vect->head) + index) = val;

            // Shift elements to the right
            for (unsigned int i = index + 1; i < (vect->size + 1); ++i) {
                *((vect->head) + i) = *(temp + i - 1);
            }

            ++(vect->size);
            ret = OK;

            // Free the temporary array
            free(temp);
        }
    }

    return ret; // Return the status
}

// Delete a value at a specific index in the vector
ret_status vect_delete_at(vector* vect, const unsigned int index)
{
    ret_status ret = NOK;

    if (NULL == vect) {
        // Do nothing if the vector is NULL
    } else {
        // Check if the index is within the vector size
        if (index < vect->size) {
            // Shift elements to the left to overwrite the deleted element
            for (unsigned int i = index; i < vect->size - 1; ++i) {
                *((vect->head) + i) = *((vect->head) + i + 1);
            }
            --(vect->size); // Decrease the vector size
            ret = OK;
        }
    }

    return ret; // Return the status
}

// Print the elements of the vector
void print(vector* vect)
{
    for (unsigned int i = 0; i < vect->size; ++i) {
        cout << "index " << i + 1 << ": " << *((vect->head) + i) << endl;
    }
    cout << "size: " << vect->size << endl;
    cout << "capacity: " << vect->capacity << endl;
}

// Access a value at a specific index in the vector
ret_status vect_at(vector* vect, const unsigned int index)
{
    ret_status ret = NOK;

    if (NULL == vect) {
        // Do nothing if the vector is NULL
    } else {
        // Check if the index is within the vector size
        if (index < vect->size) {
            cout << "index " << index << ": " << *((vect->head) + index) << endl;
            ret = OK;
        } else {
            cout << "out of range!" << endl;
        }
    }

    return ret; // Return the status
}
