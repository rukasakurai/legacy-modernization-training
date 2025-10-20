# Git & GitHub 基礎

## 学習目標
- GitとGitHubの基本ワークフロー（clone, branch, commit, push, pull, PR, review）を体験し、理解できる
- 今後のラボや実務でスムーズにコラボレーションできる共通基盤を作る

## VS CodeのUI操作について
このラボでは主にgitコマンドライン操作に焦点を当てますが、VS Codeのソース管理ビュー（GUI）でも多くの操作が可能です。GUIはバージョンや拡張機能によって画面が変わる場合があるため、詳細な手順は割愛しますが、慣れてきたらGUIも活用してみてください。
特に「Copilot Smart Actions」によるコミットメッセージ生成など、AI支援機能はUIから利用できます。

## このラボの意義
- **バージョン管理**は、誰が・いつ・どんな変更をしたかを記録し、失敗時も安全に戻れる仕組みです
- **Git**はローカルでの履歴管理、**GitHub**はクラウドでの共有・コラボレーションを担います
- チーム開発やAI支援（GitHub Copilot等）を活用する上で、基本操作の理解は不可欠です

---

## 事前準備
1. **Windows + VS Code** を使用
2. **Gitがインストール済み**で、**GitHub認証**も完了している
3. **既存のGitHubリポジトリ**にアクセス権がある

---

## ラボ1 — リポジトリをクローンしよう（clone）

### なぜ？
リモート（GitHub）上の最新ソースコードを自分のPCにコピーし、作業の土台を作ります。

### 手順
1. GitHub上の対象リポジトリのURLをコピー
2. VS Codeのターミナルで以下を実行
```
git clone <リポジトリのURL>
```
3. クローンしたフォルダをVS Codeで開く

---

## ラボ2 — 新しいブランチを作成しよう（branch）

### なぜ？
mainブランチを直接変更せず、独立した作業領域を作ることで、他の人や本番コードに影響を与えず安全に作業できます。
詳しくは[GitHub公式ドキュメント](https://docs.github.com/ja/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-branches)も参照してください。

### 手順
1. 新しいブランチを作成し、切り替える
```
git checkout -b feature/自分の名前
```
2. ブランチ名は自分の名前や目的が分かるものにしましょう

---

## ラボ3 — ファイルを編集し、コミットしよう（commit）

### なぜ？
変更内容を「スナップショット」として記録し、履歴を残します。小さな単位でコミットすることで、後から変更を追いやすくなります。
詳しくは[GitHub公式ドキュメント](https://docs.github.com/ja/pull-requests/committing-changes-to-your-project/creating-and-editing-commits/about-commits)も参照してください。

### 手順
1. 任意のファイルを編集（例: README.mdに自己紹介を1行追加）
2. 変更をステージング
```
git add README.md
```
3. コミット
```
git commit -m "Add self-introduction to README"
```

#### Copilot Smart Actions（コミットメッセージ生成）
VS Codeのソース管理ビューでコミット時に「Copilotでコミットメッセージを生成」機能を使うと、AIが変更内容を要約してメッセージを提案してくれます。
詳しくは公式ドキュメント（[Copilot Smart Actions](https://code.visualstudio.com/docs/copilot/copilot-smart-actions)）も参照してください。

---

## ラボ4 — 変更をGitHubに反映しよう（push）

### なぜ？
ローカルの変更をリモート（GitHub）にアップロードし、他の人と共有できるようにします。

### 手順
1. 現在のブランチをリモートにpush
```
git push -u origin feature/自分の名前
```
2. GitHub上で変更内容を確認
---

## ラボ5 — さらにブランチを切ってみよう（branch from branch）

1. 先ほど作成したブランチ上で、さらに新しいブランチを作成
```
git checkout -b `feature/自分の名前/experiment`
```
2. 任意のファイルを編集（例: README.mdに自己紹介を1行追加）
3. 変更をステージング
```
git add README.md
```
4. commit
```
git commit -m "<コミットメッセージ>"
```
5. push
```
git push -u origin feature/自分の名前/experiment
```

---

## ラボ6 — Pull Request（PR）を作成しよう

### なぜ？
自分の変更をチームに提案し、レビューや議論を経てmainブランチに統合するための仕組みです。
詳しくは[GitHub公式ドキュメント](https://docs.github.com/ja/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests)も参照してください。

### 手順
1. GitHubのリポジトリページを開く
2. 「Compare & pull request」ボタンをクリック
3. PRタイトル・説明を記入し、作成

---

## ラボ7 — PRのレビューを体験しよう

### なぜ？
他の人の変更内容を確認し、コメントや修正提案を行うことで、品質向上と知識共有ができます。
詳しくは[GitHub公式ドキュメント](https://docs.github.com/ja/pull-requests/collaborating-with-pull-requests/reviewing-changes-in-pull-requests/about-pull-request-reviews)も参照してください。

### 手順
1. 他の参加者のPRを1つ選び、内容を確認
2. コメントを残す、または「Approve」や「Request changes」を選択

---

## チェックリスト
- [ ] リポジトリをcloneした
- [ ] 新しいブランチを作成した
- [ ] ファイルを編集しcommitした
- [ ] さらにブランチを切った
- [ ] 変更をpushした
- [ ] PRを作成した
- [ ] 他の人のPRをレビューした

---

## 以降のラボでのgit活用

以降のラボ課題は main ブランチではなく、「feature/自分の名前」などの個別ブランチ上で作業することを推奨します。また、小まめにcommitすることを推奨します。そうすることで、変更履歴が細かく残り、後から変更内容の見直しや調整がしやすくなるほか、レビュー時も差分が分かりやすくなり、チームでのコラボレーションが円滑になります。

---

## まとめと次のステップ
- GitとGitHubは、現代のソフトウェア開発に不可欠なコラボレーション基盤です
- 基本的なワークフローを理解し、実践できれば、今後の開発やCopilot活用もスムーズに進みます
- 分からないことがあれば、遠慮なく周囲や講師に質問しましょう


---

## インストラクター向けノート
> **所要時間目安**
> - 60分
