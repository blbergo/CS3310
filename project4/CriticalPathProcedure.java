package project4;

import java.util.ArrayList;
import java.util.HashMap;

public class CriticalPathProcedure {
    // Bryan Bergo - 7/29/24
    public static double criticalPath(ArrayList<Node> nodes, HashMap<Integer, ArrayList<Integer>> graph) {
        // calculate the earliest start and finish times
        for (Node node : nodes) {
            if (graph.get(node.taskName) != null) {
                for (int i : graph.get(node.taskName)) {
                    Node child = nodes.get(i);
                    child.ES = Math.max(child.ES, node.EF);
                    child.EF = child.ES + child.timeToCompleteTask;
                }
            }
        }

        // calculate the latest start and finish times
        for (int i = nodes.size() - 1; i >= 0; i--) {
            Node node = nodes.get(i);
            if (graph.get(node.taskName) != null) {
                for (int j : graph.get(node.taskName)) {
                    Node child = nodes.get(j);
                    if (node.LS == 0) {
                        node.LS = child.LF;
                    } else {
                        node.LS = Math.min(node.LS, child.LF);
                    }
                }
                node.LF = node.LS + node.timeToCompleteTask;
            } else {
                node.LS = node.ES;
                node.LF = node.EF;
            }
        }

        // calculate the slack
        for (Node node : nodes) {
            node.slack = node.LS - node.ES;
        }

        // find the critical path
        double criticalPath = 0;
        for (Node node : nodes) {
            if (node.slack == 0) {
                criticalPath += node.timeToCompleteTask;
            }
        }

        // determine critical tasks
        ArrayList<Integer> criticalTasks = new ArrayList<>();
        for (Node node : nodes) {
            if (node.slack == 0) {
                criticalTasks.add(node.taskName);
            }
        }

        System.out.println("Critical Tasks: " + criticalTasks);

        return criticalPath;
    }

    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(0, 2));
        nodes.add(new Node(1, 4));
        nodes.add(new Node(2, 5));
        nodes.add(new Node(3, 9));
        nodes.add(new Node(4, 3));
        nodes.add(new Node(5, 2));
        nodes.add(new Node(6, 1));
        nodes.add(new Node(7, 10));
        nodes.add(new Node(8, 11));
        nodes.add(new Node(9, 6));
        nodes.add(new Node(10, 9));
        nodes.add(new Node(11, 8));
        nodes.add(new Node(12, 7));

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        graph.put(0, new ArrayList<>());
        graph.put(1, new ArrayList<>() {
            {
                add(0);
            }
        });
        graph.put(2, new ArrayList<>() {
            {
                add(0);
            }
        });
        graph.put(3, new ArrayList<>() {
            {
                add(0);
            }
        });
        graph.put(4, new ArrayList<>() {
            {
                add(1);
            }
        });
        graph.put(5, new ArrayList<>() {
            {
                add(1);
                add(2);
                add(3);
            }
        });
        graph.put(6, new ArrayList<>() {
            {
                add(4);
                add(5);
            }
        });
        graph.put(7, new ArrayList<>() {
            {
                add(5);
            }
        });
        graph.put(8, new ArrayList<>() {
            {
                add(3);
            }
        });
        graph.put(9, new ArrayList<>() {
            {
                add(6);
                add(7);
            }
        });
        graph.put(10, new ArrayList<>() {
            {
                add(8);
            }
        });
        graph.put(11, new ArrayList<>() {
            {
                add(8);
            }
        });
        graph.put(12, new ArrayList<>() {
            {
                add(9);
                add(10);
                add(11);
            }
        });

        System.out.println("Minimum Completition Time: " + criticalPath(nodes, graph));

    }
}
