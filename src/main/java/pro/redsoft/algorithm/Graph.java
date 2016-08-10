package pro.redsoft.algorithm;

import java.util.List;

/**
 * @author Alex Oreshkevich
 */
public interface Graph {

    int getVerticesNumber();

    List<Integer> adj(int v);
}
