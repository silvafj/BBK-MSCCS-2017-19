Good attempt.

```
  int saddlePointRow(int[][] array) {
        if (hasSaddlePoint(array)) {
            int[] smallest = smallestValues(array);
            for (int i = 0; i < smallest.length; ++i) {
                if (smallest[i] == largest(smallest)) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Given an array that is known to have a saddle point, returns the number of a column containing a saddle point.
     * If more than one column contains a saddle point, the first such column will be returned.
     *
     * @param array an array containing one or more saddle points.
     * @return the lowest-numbered column containing a saddle point.
     */
    int saddlePointColumn(int[][] array) {
        if (hasSaddlePoint(array)) {
            int[] largest = largestValues(array);
            for (int i = 0; i < largest.length; ++i) {
                if (largest[i] == smallest(largest)) {
                    return i;
                }
            }
        }

        return -1;
    }
```
Could these two methods have been combined via the use of an appropriately parameterised *helper* method?

```
 int[][] array = createRandomArray(rows, cols, min, max);
            if (hasSaddlePoint(array)) {
                withSaddlePoint = true;
            } else {
                withoutSaddlePoint = true;
            }

```
Try and avoid such assignments. Use negation rather than two separate boolean variables.
