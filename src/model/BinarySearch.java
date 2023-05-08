package model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Esta clase implementa el algoritmo de búsqueda binaria.
 */
public class BinarySearch<T>{

    public BinarySearch() {
    }

    /**
     * Searches for a target value within a sorted ArrayList using binary search.
     *
     * @param list        the ArrayList to search within
     * @param comparator  the Comparator to use for comparing elements in the list
     * @param targetValue the target value to search for
     * @param lowIndex    the lower index to search within (inclusive)
     * @param highIndex   the higher index to search within (inclusive)
     * @return the index of the target value if found, -1 otherwise
     */

    public int search(ArrayList<T> list, Comparator<T> comparator, T targetValue, int lowIndex, int highIndex) {
        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;
            T midValue = list.get(midIndex);
            int compareResult = comparator.compare(targetValue, midValue);
            if (compareResult < 0) {
                highIndex = midIndex - 1;
            } else if (compareResult > 0) {
                lowIndex = midIndex + 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }
    /**
     * Realiza una búsqueda binaria en una lista ordenada de objetos de tipo T y muestra los resultados por consola.
     *
     * @param list       La lista ordenada donde se realizará la búsqueda.
     * @param comparator El comparador que se utilizará para comparar los objetos en la lista.
     */
    public void coincidence(ArrayList<T> list, Comparator<T> comparator, T targetValue, int lowIndex, int highIndex) {
        int index = search(list, comparator, targetValue, lowIndex, highIndex);
        if (index == -1) {
            System.out.println("Coincidence not found.");
        } else {
            System.out.println("Coincidence founded:  " + index + ": " + list.get(index));
        }
    }


    /**
     * Busca los valores en una lista que se encuentran dentro de un rango dado.
     *
     * @param list       la lista en la que buscar los valores
     * @param comparator el comparador que se utilizará para comparar los valores en la lista
     * @param minValue   el valor mínimo del rango
     * @param maxValue   el valor máximo del rango
     * @param lowIndex   el índice más bajo de la sublista en la que se buscarán los valores
     * @param highIndex  el índice más alto de la sublista en la que se buscarán los valores
     * @return una lista de valores que se encuentran dentro del rango, o null si no se encuentra ningún valor
     */
    public ArrayList<Product> searchRangeOrInterval(ArrayList<Product> list, Comparator<Double> comparator, double minValue, double maxValue, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return null;
        }
        int midIndex = (lowIndex + highIndex) / 2;
        double midValue = list.get(midIndex).getPrice();
        int compareMin = comparator.compare(midValue, minValue);
        int compareMax = comparator.compare(midValue, maxValue);
        if (compareMin < 0) {
            return searchRangeOrInterval(list, comparator, minValue, maxValue, midIndex + 1, highIndex);
        } else if (compareMax > 0) {
            return searchRangeOrInterval(list, comparator, minValue, maxValue, lowIndex, midIndex - 1);
        } else {
            ArrayList<Product> results = new ArrayList<>();
            int left = midIndex;
            while (left > lowIndex && comparator.compare(list.get(left - 1).getPrice(), minValue) >= 0) {
                left--;
            }
            int right = midIndex;
            while (right < highIndex && comparator.compare(list.get(right + 1).getPrice(), maxValue) <= 0) {
                right++;
            }
            for (int i = left; i < right + 1; i++) {
                results.add(list.get(i));
            }
            return results;
        }
    }

    public ArrayList<Product> searchCategory(ArrayList<Product> list, Comparator<CategoryProduct> comparator, CategoryProduct category, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return null;
        }
        int midIndex = (lowIndex + highIndex) / 2;
        CategoryProduct midValue = list.get(midIndex).getCategoryProduct();
        int compare = comparator.compare(midValue, category);
        if (compare < 0) {
            return searchCategory(list, comparator, category, midIndex + 1, highIndex);
        } else if (compare > 0) {
            return searchCategory(list, comparator, category, lowIndex, midIndex - 1);
        } else {
            ArrayList<Product> results = new ArrayList<>();
            int left = midIndex;
            while (left > lowIndex && comparator.compare(list.get(left - 1).getCategoryProduct(), category) >= 0) {
                left--;
            }
            int right = midIndex;
            while (right < highIndex && comparator.compare(list.get(right + 1).getCategoryProduct(), category) <= 0) {
                right++;
            }
            for (int i = left; i < right + 1; i++) {
                results.add(list.get(i));
            }
            return results;
        }
    }

    public ArrayList<Order> searchByName(ArrayList<Order> list, Comparator<String> comparator, String name, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return null;
        }
        int midIndex = (lowIndex + highIndex) / 2;
        String midValue = list.get(midIndex).getBuyerName();
        int compare = comparator.compare(midValue, name);
        if (compare < 0) {
            return searchByName(list, comparator, name, midIndex + 1, highIndex);
        } else if (compare > 0) {
            return searchByName(list, comparator, name, lowIndex, midIndex - 1);
        } else {
            ArrayList<Order> results = new ArrayList<>();
            int left = midIndex;
            while (left > lowIndex && comparator.compare(list.get(left - 1).getBuyerName(), name) >= 0) {
                left--;
            }
            int right = midIndex;
            while (right < highIndex && comparator.compare(list.get(right + 1).getBuyerName(), name) <= 0) {
                right++;
            }
            for (int i = left; i < right + 1; i++) {
                results.add(list.get(i));
            }
            return results;
        }
    }

    public ArrayList<Order> searchRangeTotalPrice(ArrayList<Order> list, Comparator<Double> comparator, double minValue, double maxValue, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return null;
        }
        int midIndex = (lowIndex + highIndex) / 2;
        double midValue = list.get(midIndex).getTotalPrice();
        int compareMin = comparator.compare(midValue, minValue);
        int compareMax = comparator.compare(midValue, maxValue);
        if (compareMin < 0) {
            return searchRangeTotalPrice(list, comparator, minValue, maxValue, midIndex + 1, highIndex);
        } else if (compareMax > 0) {
            return searchRangeTotalPrice(list, comparator, minValue, maxValue, lowIndex, midIndex - 1);
        } else {
            ArrayList<Order> results = new ArrayList<>();
            int left = midIndex;
            while (left > lowIndex && comparator.compare(list.get(left - 1).getTotalPrice(), minValue) >= 0) {
                left--;
            }
            int right = midIndex;
            while (right < highIndex && comparator.compare(list.get(right + 1).getTotalPrice(), maxValue) <= 0) {
                right++;
            }
            for (int i = left; i < right + 1; i++) {
                results.add(list.get(i));
            }
            return results;
        }
    }
}