
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.Arrays;
import java.util.Scanner;

class edge {

    public int to, cap;
    int next;
    edge(int to,int cap,int next)
    {
        this.to=to;
        this.cap=cap;
        
        this.next=next;
    }
}

public class Main {



   

    static int cas;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a, test;
        test = scan.nextInt();
           final int N = 200;
         int INF = 0x3f3f3f3f;
        while (test != 0) {

            Main M = new Main();
            
            int cnt = 0;
             int nv = 0;
             
            edge[] g = new edge[N * N * 2];
            int[] head = new int[N];
            int[] ans = new int[N * N];
            int[] level = new int[N];
            int[] gap = new int[N];
            int[] cur = new int[N];
            
    
            int[] pre = new int[N];
            
            
            Arrays.fill(head, -1);
            int n, m;
            n = scan.nextInt();
            m = scan.nextInt();
            int ss = 0, tt = 2 * (n + m - 1) + 1;

            for (int i = 1; i <= n + m - 1; i++) {
                a = scan.nextInt();
                a = a - (min(n, i) - max(0, i - m));
            
                g[cnt]=new edge(ss,a,head[i]);
      
                g[cnt]=new edge(ss,0,head[i]);
                head[i] = cnt++;

            }
            for (int i = 1; i <= n + m - 1; i++) {
                a = scan.nextInt();
                a -= min(n, i) - max(0, i - m);
             
                g[cnt]=new edge(i + n + m - 1,a,head[tt]);
                
                head[i + n + m - 1] = cnt++;
      
                
                g[cnt]=new edge(i + n + m - 1,0,head[tt]);
                
                head[tt] = cnt++;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
              
                    
                    g[cnt]=new edge(i + j - 1,99,head[m + i - j + n + m - 1]);
                    head[i + j - 1] = cnt++;
                    
               
                    g[cnt]=new edge(i + j - 1,0, head[m + i - j + n + m - 1]);
                    head[m + i - j + n + m - 1] = cnt++;

                    ans[(i - 1) * m + j] = cnt - 1;
                }

            }
            nv = tt + 1;
          
            
        Arrays.fill(level, 0);

        Arrays.fill(gap, 0);
        Arrays.fill(cur, 0);
        System.arraycopy(head, 0, cur, 0, N);
        gap[0] = nv;
        pre[ss] = ss;
        int flow = 0, aug = INF;
        int v = ss;
        while (level[ss] < nv) {
            boolean flag = false;
            for (int i = cur[v]; i != -1; i = g[i].next) {
                int u = g[i].to;
                if (g[i].cap > 0 && level[v] == level[u] + 1) {
                    flag = true;
                    pre[u] = v;
                    v = u;
                    aug = min(aug, g[i].cap);
                    if (v == tt) {
                        flow += aug;
                        while (v != ss) {
                            v = pre[v];
                            g[cur[v]].cap -= aug;
                            g[cur[v] ^ 1].cap += aug;
                        }
                        aug = INF;
                    }
                    break;
                }
            }
            if (flag) {
                continue;
            }
            int minlevel = nv;
            for (int i = head[v]; i != -1; i = g[i].next) {
                int u = g[i].to;
                if (g[i].cap > 0 && minlevel > level[u]) {
                    minlevel = level[u];
                    cur[v] = i;
                }
            }
            if (--gap[level[v]] == 0) {
                break;
            }
            level[v] = minlevel + 1;
            gap[level[v]]++;
            v = pre[v];
        }
            
            
            
            System.out.printf("Case %d:\n", ++cas);
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    System.out.printf("%d%c", g[ans[(i - 1) * m + j]].cap + 1, j == m ? '\n' : ' ');
                }
            }
            test-=1;
        }
    }
}
