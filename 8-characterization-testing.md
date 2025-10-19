# キャラクタリゼーションテスト ラボ

レガシーシステムを生成AIで移行・リファクタリングする際、**キャラクタリゼーションテスト（characterization test）**は「仕様が不完全でも“現在の振る舞い”を守る」ための安全網です。既存システムの観察結果（入力→出力ペア）をベースラインとして固定し、その後の自動変換・最適化・リファクタリングによる意図しない挙動差（リグレッション）を検出します。本ラボでは、シンプルな既存 Java コードを題材に、この考え方を体験します。コード自体は単純ですが、あえて“ベースラインを固めてから書き換える”流れを踏むことで、より複雑なレガシー移行時のプロセスを縮図的に学べます。

---

## 学習目標
- キャラクタリゼーションテストの目的を説明できる
- 観察された振る舞い（ベースライン）をテストとして固定できる
- JUnitで入力→出力ペアをコード化できる
- リファクタリング（Stream API 等）後の同等性をテストで検証できる
- Copilotを活用してテスト・リファクタリング支援を依頼できる
- 回帰（意図しない変更）をテスト失敗から特定・分析できる

---

## 事前準備
1. **Windows + VS Code** を使用（本トレーニング標準環境）
2. **GitHub Copilot Agent Mode** を有効化
3. **JDK 21** がインストール済みであること
4. （任意）Maven プロジェクトとして進めたい場合は `mvn -v` で動作確認
5. VS Code で本ファイルを開き、ラボ手順に従う

---

## 対象となる「既存」コード（ベースライン化するコード）
以下を `src/main/java/legacy/SumCalculator.java` として配置すると仮定します（ファイルが無ければ後で作成）。

```java
public class SumCalculator {
	public int sum(int[] values) {
		int total = 0;
		for (int v : values) {
			total += v;
		}
		return total;
	}
}
```

この実装は「配列内全要素の合計を返す」という振る舞いを持っています。今後の変更（Stream API への書き換え等）前に、この振る舞いをテストで“凍結”します。

---

## ラボ1 — 既存コードの観察と理解
### 目的
現在の挙動を“言語化”し、ベースライン候補を洗い出す。
### 作業
1. コードを読み、処理手順を口頭／コメントで要約
2. 代表入力例を考える（例：`{1,2,3,4,5}` → 15, 空配列 → 0, 負数混在 `{ -2, 3 }` → 1）
3. Copilotに説明依頼

### Copilot プロンプト例
```
このクラスの挙動を要約してください。潜在的な境界ケースも列挙してください。 #file:src/main/java/legacy/SumCalculator.java
```

---

## ラボ2 — ベースライン（観察結果）のテスト化
### 目的
“現在の正しさ”をコード（JUnitテスト）として固定する。
### 作業
1. `src/test/java/legacy/SumCalculatorTest.java` を作成
2. 最低3ケース（基本 / 空 / 負数混在）をテスト
3. Copilotに追加ケース（大きな数 / ランダム）候補を提案させる

### 最低限のテスト例
```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SumCalculatorTest {
	@Test
	void sum_basic() {
		assertEquals(15, new SumCalculator().sum(new int[]{1,2,3,4,5}));
	}

	@Test
	void sum_empty() {
		assertEquals(0, new SumCalculator().sum(new int[]{}));
	}

	@Test
	void sum_withNegatives() {
		assertEquals(1, new SumCalculator().sum(new int[]{-2,3}));
	}
}
```
（Copilotに format 修正や追加テスト生成を依頼しても良い）

### Copilot プロンプト例
```
このクラスに対して境界ケースを含むJUnitテストを追加してください。 #file:src/main/java/legacy/SumCalculator.java
```

---

## ラボ3 — Copilotでテスト拡張
### 目的
AI支援で網羅性と保守性を向上させる。
### 作業
1. 重複を避けるパラメータ化テスト（JUnit 5 `@ParameterizedTest`）提案を依頼
2. ランダム入力で期待値計算する補助メソッドを生成依頼
3. “失敗しやすい罠”を Copilot に尋ねる

### プロンプト例
```
同じ期待ロジックを複数書かない形でパラメータ化テストにリファクタリングしてください。 #file:src/test/java/legacy/SumCalculatorTest.java
```

---

## ラボ4 — TDD (Red) フェーズ：新実装のテスト先行
### 目的
TDD の Red を明示的に体験し、「テストが存在しないコードを書かない」姿勢を強化する。
### 作業
1. まだ存在しない `ModernSumCalculator` を使うテストを先に書く（コンパイルエラー = Red）
2. 最小実装（単純な for ループ）でテストを通す（Green）
3. 実装は旧クラスの複製でも可（重複は後で解消）
4. Refactor フェーズは次ラボで実施

### 例：先に書くテスト（`src/test/java/legacy/ModernSumCalculatorTest.java`）
```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ModernSumCalculatorTest {
	@Test
	void sum_basic() {
		ModernSumCalculator modern = new ModernSumCalculator(); // まだ存在しない → Red
		assertEquals(15, modern.sum(new int[]{1,2,3,4,5}));
	}
}
```

### 最小 Green 実装（後で差し替える）
```java
public class ModernSumCalculator { // 一旦 Stream なし
	public int sum(int[] values) {
		int total = 0;
		for (int v : values) total += v;
		return total;
	}
}
```

### Copilot プロンプト例
```
存在しない ModernSumCalculator に対するテストを書いたので、テストが通る最小のクラス骨格を生成してください。
```

---

## ラボ5 — リファクタリング（Refactor）：モダン実装への書き換え
### 目的
TDD の Refactor として内部実装を Stream API へ置き換えつつ、振る舞いを維持する。
### 作業
`ModernSumCalculator` の本実装を Stream API に差し替え。

```java
import java.util.Arrays;

public class ModernSumCalculator {
	public int sum(int[] values) {
		return Arrays.stream(values).sum();
	}
}
```

### Copilot プロンプト例
```
forループ実装をStream APIに書き換えてください。既存テストがすべて通ることを前提にしてください。 #file:src/main/java/legacy/ModernSumCalculator.java
```

---

## ラボ6 — キャラクタリゼーションテスト：同等性検証
### 目的
旧実装と新実装が全ケースで同じ結果を返すことを確認。
### 作業
1. 並行比較用テスト `SumEquivalenceTest` を作成
2. 複数配列（固定 + ランダム）で両者の出力一致をアサート

### テスト例（抜粋）
```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Random;

class SumEquivalenceTest {
	SumCalculator legacy = new SumCalculator();
	ModernSumCalculator modern = new ModernSumCalculator();

	@Test
	void fixed_cases() {
		assertEquals(15, legacy.sum(new int[]{1,2,3,4,5}));
		assertEquals(legacy.sum(new int[]{1,2,3,4,5}), modern.sum(new int[]{1,2,3,4,5}));
		assertEquals(legacy.sum(new int[]{}), modern.sum(new int[]{}));
		assertEquals(legacy.sum(new int[]{-2,3}), modern.sum(new int[]{-2,3}));
	}

	@Test
	void random_equivalence() {
		Random r = new Random(0);
		for (int i = 0; i < 100; i++) {
			int size = r.nextInt(20);
			int[] arr = new int[size];
			for (int j = 0; j < size; j++) arr[j] = r.nextInt(200) - 100; // -100〜99
			assertEquals(legacy.sum(arr), modern.sum(arr), "Mismatch at iteration " + i);
		}
	}
}
```

### Copilot プロンプト例
```
旧実装と新実装を比較し、100個のランダム配列で結果が一致することを検証するJUnitテストを書いてください。
```

---

## ラボ7 — 回帰検出体験
### 目的
テストが“守っている”ことを実感する。
### 作業
1. `ModernSumCalculator` を意図的に壊す（例：最後の要素を加算しない）
2. テスト失敗を確認し、どのケースが検出したか分析
3. Copilotに失敗原因を説明させる

### 例：誤った変更
```java
// 誤った例（最後の要素を除外）
public int sum(int[] values) {
	return java.util.stream.IntStream.range(0, Math.max(0, values.length - 1))
		.map(i -> values[i])
		.sum();
}
```
→ ランダムテスト / 固定ケースの不一致で検出可能。

### Copilot プロンプト例
```
テストが失敗しました。原因を説明し、修正案を提示してください。 #file:src/main/java/legacy/ModernSumCalculator.java
```

---

## ラボ8 — 追加発展（任意）
### アイデア
- `int[]` を `List<Integer>` 化してジェネリクス対応
- 空/巨大配列性能比較（`System.nanoTime()` で粗い測定）
- `OptionalInt` を返す（空配列時に値なし）版を試す
- 並列ストリーム `Arrays.stream(values).parallel().sum()` の挙動比較

### Copilot プロンプト例
```
OptionalIntを使い、空配列なら空を返すsumメソッドを追加してください。
```

---

## キャラクタリゼーションテストの要点まとめ
- 仕様が不明確でも「現時点の振る舞い」をテストで固定すれば安心して書き換え可能
- テストが“意図しない差分”のみを浮き彫りにするフィルターになる
- 小さな題材でも流れ（観察→固定→変更→比較→回帰検出）を体験することが重要

---

## チェックリスト
- [ ] 既存コードの挙動を説明した
- [ ] ベースライン（基本/空/負数）テストを作成した
- [ ] Copilotで追加ケース／改善提案を受けた
- [ ] Stream API版を作成した
- [ ] 旧版と新版の同等性テストを作成・実行した
- [ ] 意図的なバグを入れて回帰検出を確認した
- [ ] 任意の発展課題を1つ以上試した

---

## インストラクター向けノート
> **所要時間目安**
> - 25–35分（参加者のJUnit経験により変動）
>
> **参加者レベル別の工夫**
> - 初心者：テスト3ケース + Stream API 書き換えまで
> - 経験者：パラメータ化テスト・ランダム比較・意図的バグ挿入 + 発展課題
>
> **よくある詰まりポイント**
> - “単純だからテスト不要”と思いがち → 小さい題材で流れを定着させる意義を強調
> - 配列 vs コレクション混在 → テストはシンプルな `int[]` で統一
> - ランダムテストの再現性 → 乱数シード固定 (`new Random(0)`) を推奨

---

## 次のステップ
- より複雑な集計（平均、中央値、異常値除去）へ拡張
- COBOL → Java 変換後コードにも同手法を適用
- ビジネスロジックが不透明なモジュールへ適用し、仕様化の足掛かりにする

---

このラボを完了すると、シンプルな例でもキャラクタリゼーションテストが“安心して変えるための工程”であることを体験的に理解できます。複雑さよりも「流れ」を習得することが最重要です。


