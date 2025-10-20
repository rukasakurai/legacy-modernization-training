# COBOLコード解析

## 学習目標
- 既存COBOLプログラムの依存関係やサブルーチン構造をGitHub Copilotで解析できる
- レガシーコードの理解・モダナイゼーションの第一歩を体験する

---

## 事前準備
1. **Windows + VS Code** を使用
2. **GitHub Copilot Agent Mode**が有効
3. ワークスペース直下の `legacy/` フォルダに [dbstat.sqb](https://www.ibm.com/docs/ja/db2/11.5.x?topic=SSEPGG_11.5.0/com.ibm.db2.luw.apdv.sample.doc/doc/cobol/s-dbstat-sqb.htm) をダウンロードして保存
4. 必要に応じて `legacy/` フォルダに [checkerr.cbl](https://www.ibm.com/docs/ja/db2/11.5.x?topic=SSEPGG_11.5.0/com.ibm.db2.luw.apdv.sample.doc/doc/cobol/s-checkerr-cbl.htm) も保存

---

## ラボ1 — 依存関係を解析してみよう

### なぜ？
COBOLプログラムは外部ファイルやライブラリに依存することが多く、実行環境の把握が重要です。

### プロンプト例
補足: ここで GitHub Copilot のチャット変数 `#file:` を使います。指定したファイル内容をプロンプトに参照させたい時に利用します。詳細: https://docs.github.com/ja/copilot/reference/cheat-sheet#chat-variables
```
#file:legacy/dbstat.sqb このプログラムの実行に必要な依存関係を解析して下さい。
```

---

## ラボ2 — ワークスペース全体の説明をAIに依頼しよう

### なぜ？
複数ファイルがある場合、全体像を把握することで効率的な解析やモダナイゼーションが可能になります。

### プロンプト例
補足: ここで GitHub Copilot のチャット参加者 `@`やスラッシュ コマンド `/` を使います。専門知識を持った「参加者」の追加に時にチャット参加者 `@`を使い、良く使われるプロンプトの省略にスラッシュ コマンド `/` を使います。詳細: https://docs.github.com/ja/copilot/reference/cheat-sheet
```
@workspace /explain
```

---

## ラボ3 — サブルーチンの説明をAIに依頼しよう

### なぜ？
COBOLはサブルーチン（PERFORMやCALL）で処理を分割するため、各サブルーチンの役割を理解することが重要です。

### プロンプト例
```
このファイルに定義されているサブルーチンの説明をしてください。#file:legacy/dbstat.sqb
```

---

## 応用例 — 他のCOBOLファイルも同様に解析できる

例えば [checkerr.cbl](https://www.ibm.com/docs/ja/db2/11.5.x?topic=SSEPGG_11.5.0/com.ibm.db2.luw.apdv.sample.doc/doc/cobol/s-checkerr-cbl.htm) も同じ手順・プロンプトで依存関係やサブルーチン構造を解析できます。

---

## チェックリスト
- [ ] dbstat.sqbをダウンロードし、ワークスペースに追加した
- [ ] 依存関係解析プロンプトを実行した
- [ ] ワークスペース全体説明プロンプトを実行した
- [ ] サブルーチン説明プロンプトを実行した
- [ ] checkerr.cblでも同様の解析を試した

---

## まとめと次のステップ
- GitHub Copilotを活用することで、COBOLなどレガシーコードの解析・理解が効率化できます
- 依存関係やサブルーチン構造の把握は、モダナイゼーションの第一歩です
- 他のCOBOLファイルや大規模コードにも応用できます

---

## インストラクター向けノート
> **所要時間目安**
> - 20–25分
>
> **参加者レベル別の工夫**
> - 経験者：複数ファイルの依存関係やサブルーチンの相互関係まで深掘り
> - 初心者：まずは1ファイル単位でAI解析を体験

