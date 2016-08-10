package pro.redsoft.algorithm;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.Assert.assertEquals;

/**
 * @author Alex Oreshkevich
 */
public class ConnectedComponentsTest {

    @Test
    public void test() throws Exception {

        // "scc.txt.zip"
        Digraph tinyDG = new Digraph(readFromFile("tinyDG.txt"));
        //System.out.println("Source graph");
        //System.out.println(tinyDG);

        //System.out.println("Reversed graph");
        //System.out.println(tinyDG.reverse());

        KosarajuSCC scc = new KosarajuSCC(tinyDG);
        // number of connected components
        int m = scc.count();
        System.out.println(m + " components");

        // compute list of vertices in each strong component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<>();
        }
        for (int v = 0; v < tinyDG.getVerticesNumber(); v++) {
            components[scc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testCase1() throws Exception {
        doTest("testDG1.txt");
    }

    @Test
    public void testCase2() throws Exception {
        doTest("testDG2.txt");
    }

    @Test
    public void testCase3() throws Exception {
        doTest("tinyDG3.txt");
    }

    @Test
    public void finalTest() throws Exception{
        doTest("scc.txt", false);
    }

    public void doTest(String fileName) throws Exception {
        doTest(fileName, false);
    }

    public void doTest(String fileName, boolean zip) throws Exception {

        Digraph digraph;

        if (!zip)   {
            digraph = new Digraph(875714, readFromFile(fileName));
        }
        else {
            digraph = new Digraph(875714, readFromZip(fileName));
        }

        assertEquals(875714, digraph.getVerticesNumber());
        assertEquals(8, digraph.adj(1).size());

        KosarajuSCC scc = new KosarajuSCC(digraph);
        List<Integer> componentSizes = new LinkedList<>();

        // compute list of vertices in each strong component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[scc.count()];
        for (int i = 0; i < scc.count(); i++) {
            components[i] = new Queue<>();
        }
        for (int v = 0; v < digraph.getVerticesNumber(); v++) {
            components[scc.id(v)].enqueue(v);
        }

        // save as list all component sizes
        for (int i = 0; i < scc.count(); i++) {
            componentSizes.add(components[i].size());
        }

        Collections.sort(componentSizes);

        System.out.println();
        System.out.println("Component sizes (total = " + componentSizes.size() + ")->\n");

        //if (componentSizes.size() > 5){
        //    componentSizes = componentSizes.subList(componentSizes.size() - 5, componentSizes.size());
        //}

        for (int i = componentSizes.size() - 1, k = 0; i >= 0 && k < 6; i--, k++) {

            int size = componentSizes.get(i);
            //if (i != 0 && size != 1) {
                System.out.print(componentSizes.get(i));
            //}
            //else {
            //    System.out.print(0);
            //}
            if (i != 0) {
                System.out.print(",");
            }
        }
    }

    private InputStream readFromZip(String fileName) throws URISyntaxException, IOException {
        File archive = new File(ClassLoader.getSystemClassLoader().getResource(fileName).toURI());
        ZipFile zipFile = new ZipFile(archive);

        InputStream inputStream;

        ZipEntry zipEntry = zipFile.entries().nextElement();
        inputStream = zipFile.getInputStream(zipEntry);
        return inputStream;
    }

    private InputStream readFromFile(String fileName) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
    }
}
