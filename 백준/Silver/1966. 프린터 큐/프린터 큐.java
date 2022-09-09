import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.valueOf(br.readLine());
    for (int t = 0; t < n; t++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int length = Integer.valueOf(st.nextToken());
      int target = Integer.valueOf(st.nextToken());

      Integer nums[] = new Integer[length];
      st = new StringTokenizer(br.readLine());
      Queue<Document> queue = new LinkedList<>();
      Queue<Integer> priorityQueue = new LinkedList<>();
      Document[] documents = new Document[length];

      for (int i = 0; i < length; i++) {
        nums[i] = Integer.valueOf(st.nextToken());
        documents[i] = new Document(i, nums[i]);
        queue.add(new Document(i, nums[i]));
      }

      Arrays.sort(nums, Collections.reverseOrder());

      for (int i = 0; i < length; i++) {
        priorityQueue.add(nums[i]);
      }

      int count = 0;
      while (true) {
        int max = priorityQueue.poll();
        Document document = new Document();
        for (int i = 0; i < queue.size(); i++) {
          document = queue.poll();
          if (document.priority == max) {
            count++;
            break;
          } else {
            queue.add(document);
          }
        }
        if (document.index == target) {
          break;
        }
      }
      bw.write(String.valueOf(count));
      bw.newLine();
    }
    bw.flush();
    bw.close();
    br.close();
  }
}

class Document {
  int index;
  int priority;

  public Document() {
  }

  public Document(int index, int priority) {
    this.index = index;
    this.priority = priority;
  }
}