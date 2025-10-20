# Java TDD ラボ（VS Code + GitHub Copilot）

## 学習目標
- VS Code 上で **テスト駆動開発 (TDD)** の基本サイクル（Red → Green → Refactor）を体験できる
- GitHub Copilot を使って **テストコード生成支援** と **実装補完** を活用する方法を理解する
- Java + Maven プロジェクトの最小構成を把握し、テスト実行方法を習得する

---

## 事前準備
1. **Windows + VS Code** を使用
2. 拡張機能がインストール済み：
   - Extension Pack for Java (または個別に Java 拡張一式)
   - GitHub Copilot / GitHub Copilot Chat / GitHub Copilot Agent Mode
3. **Java 21 (または 17 以上)** が `java -version` で利用可能
4. **Maven** がインストール済み（`mvn -v`）
5. ワークスペースフォルダを VS Code で開いている

---

## このラボで扱う題材
最小サンプルとして `StringCalculator` クラスを題材に以下を段階的に実装：
- 2つの整数を加算する `add(int a, int b)`
- 2つの文字列を連結する `concat(String a, String b)`
- 整数リストから最大値を返す `max(List<Integer> values)`

各機能は「テストを書く → 失敗を確認 → 実装 or 修正 → 成功」という TDD サイクルで進めます。

---

## ラボ全体の流れ（概要）
1. Maven プロジェクトを作成（GitHub Copilot支援も可）
2. 最初のテストクラス作成（Red）
3. 最小実装（Green）
4. 追加テストと機能拡張（Red → Green 繰り返し）
5. リファクタリング（必要なら）
6. GitHub Copilot によるテスト生成支援の比較

---

## ラボ1 — プロジェクトを用意しよう

### なぜ？
テスト駆動開発の反復を行うための最小構成（`pom.xml`, `src/main/java`, `src/test/java`）を準備します。

### 手順
1. VS Code のターミナル（PowerShell）で空のディレクトリを作成（任意）
2. GitHub Copilot Agent Mode で以下のように依頼（例）：
   ```
   Java 21 を想定した最小の Maven プロジェクトを作成してください。JUnit 5 を使えるようにしてください。ディレクトリ構成も含めて生成して。
   ```
3. 生成された `pom.xml` に `junit-jupiter` 依存が入っているか確認
4. もし未生成なら、以下の最小例を参考に追加（確認だけでよい）：
   ```xml
   <dependencies>
     <dependency>
       <groupId>org.junit.jupiter</groupId>
       <artifactId>junit-jupiter</artifactId>
       <version>5.10.2</version>
       <scope>test</scope>
     </dependency>
   </dependencies>
   <build>
     <plugins>
       <plugin>
         <groupId>org.apache.maven.plugins</groupId>
         <artifactId>maven-surefire-plugin</artifactId>
         <version>3.2.5</version>
       </plugin>
     </plugins>
   </build>
   ```
5. VS Code 左サイドバー「Testing」ビューにテストが表示される仕組みを後で体験します。

---

## ラボ2 — 最初のテストを書こう（Red）

### なぜ？
テストを先に書くことで仕様を明確化し、不要な実装を避けられます。

### 手順
1. `src/test/java` に `StringCalculatorTest` を作成
2. 空のテストメソッドを書き始めて GitHub Copilot の提案を誘発：
   ```java
   import org.junit.jupiter.api.Test;
   import static org.junit.jupiter.api.Assertions.*;

   class StringCalculatorTest {
       @Test
       void add_二つの整数を加算できる() {
           // Arrange
           // Act
           // Assert
       }
   }
   ```
3. カーソル行で以下のようにプロンプト（インライン or Chat）：
   ```
   2つの整数 100 と 200 を add で加算し 300 を検証するテスト本文を補完してください。
   ```
4. 生成されたアサーション例：
   ```java
   int result = new StringCalculator().add(100, 200);
   assertEquals(300, result);
   ```
5. まだ `StringCalculator` が存在しないためコンパイルエラー（＝Red）

---

## ラボ3 — 最小実装でテストを通そう（Green）

### なぜ？
テストが失敗している状態から、必要最小限のコードでグリーンにします。

### 手順
1. `src/main/java` に `StringCalculator` を作成
   ```java
   public class StringCalculator {
       public int add(int a, int b) {
           return a + b; // 最小実装
       }
   }
   ```
2. テストを実行：
   - 方法A: エディタ上の `Run Test` / テストアイコン
   - 方法B: Testing ビューから実行
   - 方法C: ターミナル
     ```powershell
     mvn -q -DskipTests=false test
     ```
3. 成功したら次へ進む

---

## ラボ4 — 2番目の機能 concat を TDD で追加

### なぜ？
同じパターンを繰り返すことで TDD リズムを定着させます。

### 手順
1. 追加テストメソッド雛形：
   ```java
   @Test
   void concat_二つの文字列を連結できる() {
       StringCalculator calc = new StringCalculator();
       String result = calc.concat("Hello", "World");
       assertEquals("HelloWorld", result);
   }
   ```
2. 当然まだ失敗（メソッド未定義）
3. 本体に追加：
   ```java
   public String concat(String a, String b) {
       return a + b;
   }
   ```
4. テスト再実行 → Green

---

## ラボ5 — 3番目: リスト中の最大値取得 max(List<Integer>)

### なぜ？
少し複雑な処理で GitHub Copilot の補完力を確認します。

### 手順
1. テストから書く：
   ```java
   @Test
   void max_整数リストから最大値を返す() {
       var values = java.util.List.of(5, 99, 12, 77);
       int result = new StringCalculator().max(values);
       assertEquals(99, result);
   }
   ```
2. GitHub Copilot に実装を提案させるため `max` を呼び出した後に `StringCalculator` クラスへ移動し、`public int max(List<Integer> values)` と書き始める
3. 期待候補例：
   ```java
   public int max(java.util.List<Integer> values) {
       if (values == null || values.isEmpty()) throw new IllegalArgumentException("values is empty");
       int m = values.get(0);
       for (int v : values) {
           if (v > m) m = v;
       }
       return m;
   }
   ```
4. テスト実行 → Green

---

## ラボ6 — GitHub Copilot にテスト生成を丸ごと頼んで比較

### なぜ？
人間が逐次書いた場合との「網羅性」「命名」「冗長さ」の違いを比較し、AI 提案を取捨選択する姿勢を学ぶため。

### 手順
1. Chat でプロンプト：
   ```
   StringCalculator クラスに対する追加の境界テストや例外ケースの JUnit5 テストをいくつか提案してください。
   ```
2. 例外ケース (`max` に空リスト渡す) などが出てきたら、必要なものだけ採用
3. 採用基準を口頭/メモで整理（例: 可読性 / 重複回避 / 価値）

---

## ラボ7 — リファクタリング（任意）

### なぜ？
テストが守りとなるため、安全に改善できることを体感します。

### 例
- `max` を `values.stream().mapToInt(i->i).max().orElseThrow(...)` に書き換え → テスト再実行で安全確認
- `concat` の命名を `concat` → `concatStrings` に変更した場合の影響把握（テスト修正）

---

## GitHub Copilot プロンプト例（貼って使える）
```
add メソッドのテストを Arrange / Act / Assert コメント付きで改善してください。
```
```
StringCalculator の max メソッドに対する異常系テストを3つ提案してください。JUnit5形式で。
```
```
このテストクラスの重複を減らすために共通化案を説明してください。
```
```
TDD の Red/Green/Refactor の各段階で注意すべきポイントを簡潔にまとめてください。
```

---

## チェックリスト
- [ ] Maven プロジェクトを作成した
- [ ] 最初の `add` テストを書いて失敗を確認した
- [ ] 最小実装でテストを通した
- [ ] `concat` 機能を TDD で追加した
- [ ] `max` 機能を TDD で追加した
- [ ] GitHub Copilot に追加テスト案を依頼した
- [ ] （任意）リファクタリング後もテストが通ることを確認した

---

## まとめ

## まとめ
- TDD は「テスト先行で仕様を固定 → 最小実装 → 安全な改善」のリズム
- GitHub Copilot は「型/定型/反復」の補完を高速化し、人は仕様・品質・意図へ集中できる
- 提案を無批判に受け入れず、目的（読みやすさ / 網羅性 / シンプルさ）で判断する姿勢が重要

---

## 参考 / 出典
本ラボは以下の公開ドキュメントを参考に、日本語化・手順再構成を行った派生コンテンツです。
- https://github.com/ps-copilot-sandbox/copilot-demo-github-java/blob/main/Demos/TDD-IntelliJ/README.md

## 次のステップ（発展アイデア）
- 例外設計（`max` の null / 空対応）をテストドリブンで拡張
- ParameterizedTest を導入して加算・最大値テストの重複削減
- Jacoco などカバレッジ計測 → GitHub Copilot に「未カバー行へのテスト提案」を依頼
- GitHub Actions で CI を追加（Push 時に `mvn test` 実行）

---

## インストラクター向けノート
> **所要時間目安** 25–35分  
> **難易度調整**  
> - 初心者: `add` と `concat` までで区切る  
> - 中級: 例外ケース / ParameterizedTest まで  
> - 上級: リファクタリング + ストリーム実装 + 追加境界テスト  
> **観察ポイント**: GitHub Copilot 提案をそのまま貼るのではなく、命名/例外処理/過剰テストを取捨選択できているか
