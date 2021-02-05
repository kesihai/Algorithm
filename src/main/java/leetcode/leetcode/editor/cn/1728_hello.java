//package leetcode.leetcode.editor.cn;
////一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。
////
//// 它们所处的环境设定是一个 rows x cols 的方格 grid ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。
////
////
//// 玩家由字符 'C' （代表猫）和 'M' （代表老鼠）表示。
//// 地板由字符 '.' 表示，玩家可以通过这个格子。
//// 墙用字符 '#' 表示，玩家不能通过这个格子。
//// 食物用字符 'F' 表示，玩家可以通过这个格子。
//// 字符 'C' ， 'M' 和 'F' 在 grid 中都只会出现一次。
////
////
//// 猫和老鼠按照如下规则移动：
////
////
//// 老鼠 先移动 ，然后两名玩家轮流移动。
//// 每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 grid 。
//// catJump 和 mouseJump 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。
//// 它们可以停留在原地。
//// 老鼠可以跳跃过猫的位置。
////
////
//// 游戏有 4 种方式会结束：
////
////
//// 如果猫跟老鼠处在相同的位置，那么猫获胜。
//// 如果猫先到达食物，那么猫获胜。
//// 如果老鼠先到达食物，那么老鼠获胜。
//// 如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。
////
////
//// 给你 rows x cols 的矩阵 grid 和两个整数 catJump 和 mouseJump ，双方都采取最优策略，如果老鼠获胜，那么请你返回 tr
////ue ，否则返回 false 。
////
////
////
//// 示例 1：
////
////
////
////
////输入：grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
////输出：true
////解释：猫无法抓到老鼠，也没法比老鼠先到达食物。
////
////
//// 示例 2：
////
////
////
////
////输入：grid = ["M.C...F"], catJump = 1, mouseJump = 4
////输出：true
////
////
//// 示例 3：
////
////
////输入：grid = ["M.C...F"], catJump = 1, mouseJump = 3
////输出：false
////
////
//// 示例 4：
////
////
////输入：grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
////输出：false
////
////
//// 示例 5：
////
////
////输入：grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump =
////1
////输出：true
////
////
////
////
//// 提示：
////
////
//// rows == grid.length
//// cols = grid[i].length
//// 1 <= rows, cols <= 8
//// grid[i][j] 只包含字符 'C' ，'M' ，'F' ，'.' 和 '#' 。
//// grid 中只包含一个 'C' ，'M' 和 'F' 。
//// 1 <= catJump, mouseJump <= 8
////
//// Related Topics 动态规划
//// 👍 4 👎 0
//
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//
////leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//    int[][] mouseStep;
//    int[][] catStep;
//    String[] grid;
//    int tx, ty;
//    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
//        this.grid = grid;
//        mouseStep = new int[grid.length][grid[0].length()];
//        catStep = new int[grid.length][grid[0].length()];
//        for (int i = 0; i < grid.length; i++) {
//            Arrays.fill(mouseStep[i], -1);
//            Arrays.fill(catStep[i], -1);
//        }
//        int x = 0, y = 0, xx = 0, yy = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length(); j++) {
//                char ch = grid[i].charAt(j);
//                if (ch == 'C') {
//                    x = i;
//                    y = j;
//                } else if (ch == 'M') {
//                    xx = i;
//                    yy = j;
//                } else if (ch == 'F') {
//                    tx = i;
//                    ty = j;
//                }
//            }
//        }
//        catRun(x, y, catJump);
//        mouseRun(xx, yy, mouseJump);
//        return mouseStep[tx][ty] != -1;
//    }
//
//    private void mouseRun(int x, int y, int step) {
//        Item item = new Item(x, y, 0);
//        mouseStep[x][y] = step;
//        Queue<Item> q = new LinkedList<>();
//        q.add(item);
//        while (!q.isEmpty()) {
//            item = q.poll();
//            for (int i = 0; i < dir.length; i++) {
//                for (int len = 1; len <= step; len ++) {
//                    int xx = item.x + len * dir[i][0];
//                    int yy = item.y + len * dir[i][1];
//                    if (!check(xx, yy)) {
//                        continue;
//                    }
//                    if (grid[xx].charAt(yy) == '#') {
//                        break;
//                    }
//                    int tmp = item.step + 1;
//                    if (mouseStep[xx][yy] != -1 && mouseStep[xx][yy] <= tmp) {
//                        continue;
//                    }
//                    if (catStep[xx][yy] != -1 && catStep[xx][yy] < tmp) {
//                        continue;
//                    }
//                    if (catStep[xx][yy] == tmp && !(xx == tx && yy == ty)) {
//                        continue;
//                    }
//                    if (tmp > 1000) {
//                        continue;
//                    }
//                    mouseStep[xx][yy] = tmp;
//                    q.add(new Item(xx, yy, tmp));
//                }
//            }
//        }
//    }
//
//    private void catRun(int x, int y, int step) {
//        Item item = new Item(x, y, 0);
//        catStep[x][y] = step;
//        Queue<Item> q = new LinkedList<>();
//        q.add(item);
//        while (!q.isEmpty()) {
//            item = q.poll();
//            for (int i = 0; i < dir.length; i++) {
//                for (int len = 1; len <= step; len ++) {
//                    int xx = item.x + len * dir[i][0];
//                    int yy = item.y + len * dir[i][1];
//                    if (!check(xx, yy)) {
//                        continue;
//                    }
//                    if (grid[xx].charAt(yy) == '#') {
//                        break;
//                    }
//                    int tmp = item.step + 1;
//                    if (catStep[xx][yy] != -1 && catStep[xx][yy] <= tmp) {
//                        continue;
//                    }
//                    catStep[xx][yy] = tmp;
//                    q.add(new Item(xx, yy, tmp));
//                }
//            }
//        }
//    }
//
//    private static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//
//    private static class Item {
//
//        public Item(int x, int y, int step) {
//            this.x = x;
//            this.y = y;
//            this.step = step;
//        }
//
//        int x, y;
//        int step;
//    }
//
//    private boolean check(int x, int y) {
//        return x >= 0 && x < mouseStep.length && y >= 0 && y < mouseStep[0].length;
//    }
//}
//
//int dp[N][M][N][M]
////leetcode submit region end(Prohibit modification and deletion)
