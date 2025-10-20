# 生成AIを活用したレガシーシステム移行の基礎トレーニング

本トレーニングは、生成AIを活用し、レガシーシステム移行の基礎を実践的に学ぶことを目的としています。座学とハンズオンを通じて安全かつ効率的なモダナイゼーション手法を体験します。

なお、生成AIツールは多岐にわたりますが、本研修ではGitHub Copilotを中心に扱います。加えて、Azure OpenAI ServiceなどAzure AIサービスについても簡単にご紹介します。

本研修はCOBOLからJavaへの移行を中心にしていますが、ここで学ぶ考え方や手法は、他の言語・プラットフォーム間のレガシー資産のモダナイゼーションや再構築プロジェクトにも幅広く応用可能です。

# 研修の流れ（概要）
本研修は 2 日間 (Day1 / Day2) で、座学 → 基礎ハンズオン → 応用/改善 → ふりかえり の学習サイクルを反復しながら、COBOL資産の読み解きと Java への段階的移行、および GitHub Copilot を安全かつ効果的に使う実践力を身につけます。

## Day1 (09:00 - 17:00 予定)

- 09:00-13:00:
  - はじめに
  - [GitHub Copilot チャット & エージェントモード入門](1-github-copilot-intro.md)
  - [レガシーシステムとモダナイゼーション全体像](2-legacy-modernization-overview.md)
  - [GitHub Copilot を活用したモダナイゼーションのプランニング](3-planning-for-modernization.md)
  - [Git & GitHub 基礎ラボ](4-git-github-basics.md)
  - [GitHub Copilot のリポジトリ カスタム命令を追加する](5-copilot-instructions.md)
  - 質疑 / 遅延調整バッファ
- 13:00-14:00: 昼休憩
- 14:00-17:00
  - [COBOLコード解析ラボ](6-understanding-existing-cobol-code.md)
  - [COBOL→Java変換ラボ](7-cobol-to-java-lab.md)
  - Day1 振り返り (KPT / 改善抽出)

## Day2 (09:00 - 17:00 予定)

- 09:00-13:00
  - Day1 振り返り
  - [キャラクタリゼーションテスト](8-characterization-testing.md)
  - [TDD](9-tdd.md)
  - [DevSecOps](10-devsecops.md)
- 13:00-14:00: 昼休憩
- 14:00-17:00
  - [Agentic DevOps](11-agentic-devops.md)
  - まとめ と 振り返り

---
（本概要は事前説明用ドラフトです。参加者スキル状況・時間配分により当日一部変更となる場合があります。）

## 学習ゴール（Learning Outcomes）
1. レガシー移行における代表的アプローチの違いと選択観点を説明できる
2. COBOL 断片をプロンプトを介して要約・構造化し Java への変換方針を立案できる
3. GitHub Copilot を利用した骨格生成と段階的リファクタリングの進め方を再現できる
4. 自動生成コードの信頼性/保守性/セキュリティの初歩的評価観点を列挙できる
5. 良いプロンプト（目的/制約/期待形式/検証手段）の基本パターンを運用できる

## 前提条件（参加者）
- GitHub アカウント / GitHub Copilot ライセンス有効
- OpenJDK 21 & Maven & VS Code & Git が動作確認済み
- Java または他言語での基本的プログラミング経験（未経験者は補助資料でキャッチアップ可）
