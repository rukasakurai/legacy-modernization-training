# GitHub Copilot のリポジトリ カスタムインストラクションを追加する

## 学習目標
- GitHub Copilot用のリポジトリカスタムインストラクションを追加・編集できる
- インストラクションの適用状況を確認できる

## この機能とは（ファクト）
- GitHub Copilotのリポジトリカスタムインストラクションは、リポジトリ全体に適用されるガイドラインやルールを記述するファイルです。[GitHub Docs: リポジトリのカスタムインストラクションを追加する](https://docs.github.com/ja/copilot/how-tos/configure-custom-instructions/add-repository-instructions)
- ファイル名と場所：`.github/copilot-instructions.md`（リポジトリ直下または.githubディレクトリ内）



## ラボ — カスタムインストラクションの作成とテスト
1. `.github/copilot-instructions.md` を作成
2. 以下のテンプレートを貼り付け、保存

```markdown
# Repository Custom Instructions for GitHub Copilot

## Language Preference

このリポジトリの人向けコンテンツ（README, コメント, ドキュメント等）は日本語で記述してください。
```

3. GitHub Copilotに指示を出し、日本語で出力されるか確認

4. 任意のインストラクションを追加し、挙動を確認

5. gitでコミット
```
git checkout ブランチ名
git add .github/copilot-instructions.md
git commit -m "Add GitHub Copilot repository custom instructions"
git push
```

6. push後、GitHub上でファイルが追加されたか確認

### チェックリスト
- [ ] `.github/copilot-instructions.md` を作成した
- [ ] テンプレートを貼り付けた
- [ ] GitHub Copilotが日本語で出力するか確認した

## インストラクター向けノート
> **所要時間目安**
> - 20–25分
>
> **参加者レベル別の工夫**
> - 経験者：独自ルール追加や応用例を考える

