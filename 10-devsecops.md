# DevSecOps

レガシーシステムの近代化を進める際、単に新しい技術へ移行するだけでは十分ではありません。安全性や信頼性を確保しながら、開発と運用が協力して進めることが重要です。最近では、ツールや自動化の進化により、設計や開発の初期段階からリスクや問題を早く発見しやすくなっています。これにより、後工程での手戻りやトラブルを減らし、全員が安心して新しい仕組みに取り組める環境が整いつつあります。現場のスキル差や環境の違いがあっても、早い段階で確認・対応できる体制が、より実現しやすくなっています。

マイクロソフトは、DevSecOpsに役に立つ様々な技術をGitHubやAzureで提供しています。全体像は[Microsoft公式ドキュメント](https://azure.microsoft.com/ja-jp/solutions/devsecops)をご参照ください。


## 主なリスクとGitHub機能による対策

GitHubのセキュリティ機能の全体像・詳細は[GitHub公式ドキュメント](https://docs.github.com/ja/enterprise-cloud@latest/code-security/getting-started/github-security-features>) に整理されています。以下の表では、その代表的な機能を要約するとともに、公式には「セキュリティ機能」として分類されていないものの結果的にセキュリティ強化へ寄与するGitHub機能も含めています。

| リスク | GitHub機能による対策 |
|--------|----------------------|
| 機密情報（シークレット）の漏洩 | [シークレットスキャン（Secret Scanning）](https://docs.github.com/ja/enterprise-cloud@latest/code-security/secret-scanning/introduction/about-secret-scanning)で自動検出・通知等|
| 依存関係の脆弱性 | [Dependabot](https://docs.github.com/ja/enterprise-cloud@latest/code-security/dependabot/ecosystems-supported-by-dependabot/supported-ecosystems-and-repositories)による自動更新・脆弱性アラート |
| アプリケーションコードの脆弱性 | [CodeQL](https://docs.github.com/ja/enterprise-cloud@latest/code-security/code-scanning/introduction-to-code-scanning/about-code-scanning-with-codeql) |
| レビュー漏れ | [プルリクエストレビュー](https://docs.github.com/ja/enterprise-cloud@latest/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/requesting-a-pull-request-review) によるレビューの徹底 |
| 人的ミス | [GitHub Actions](https://docs.github.com/ja/enterprise-cloud@latest/actions/get-started/understand-github-actions) による自動化 |
| 安全でないコーディング | [GitHub Copilotのカスタムインストラクション](https://docs.github.com/ja/enterprise-cloud@latest/copilot/how-tos/configure-custom-instructions/add-repository-instructions)で安全なコーディングを促進 |

## ラボ: GitHub Copilotを使ったセキュリティ指示の活用

このラボでは、GitHub Copilotのカスタムインストラクション機能を使い、セキュリティ観点（OWASP Top 10など）を意識したコーディングを体験します。

参考: [Secure Coding and OWASP Guidelines](https://github.com/github/awesome-copilot/blob/main/instructions/security-and-owasp.instructions.md)

### 演習
1. Security関連のGitHub Copilotのカスタムインストラクションがない状態で、コード生成を依頼
```
Azure OpenAI Serviceに"Hello"とプロンプトをするJavaのコンソールアプリを書いて。必要な変数は環境変数にあるという前提で書いて。
```
Key認証を使った実装になっている可能性が高いです。Key認証は、検証時等は手軽で便利ですが、Key流出リスクを抱えるシステムになるため、本番ではなるべくキーレス認証にした方がセキュリティ向上に繋がります。

2. GitHub Copilotのカスタムインストラクションに指示を追加
```
Azureに接続する際はシークレットではなく、キーレス認証を使ってください。
```

3. 最初に生成されたコードを削除して、再度コード生成を依頼
```
Azure OpenAI Serviceに"Hello"とプロンプトをするJavaのコンソールアプリを書いて。必要な変数は環境変数にあるという前提で書いて。
```
キーレス認証を使った実装になっている可能性が高いです。
> 注意: 人と同じく、AIもなるべくカスタムインストラクション等のガイドラインに従おうとはしますが、すべてのインストラクションに確実に従うとは限りません。カスタムインストラクションを活用して「[シフトレフト](https://azure.microsoft.com/ja-jp/solutions/devsecops)」(時間が左から右に流れるとイメージした際に、より早く、つまり早期に、セキュリティ脆弱性を防ぐこと)しつつ、その他のGitHubセキュリティ機能を併用することを強く推奨します。
4. その他の気になる脆弱性で1-3を繰り返す。

## インストラクター向けノート
> **所要時間目安**
> - 60分


