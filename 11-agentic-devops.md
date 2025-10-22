# Agentic DevOps
Agentic DevOpsは、AIエージェントを活用してソフトウェア開発や運用の自動化・最適化・効率化を図るDevOpsの新しいアプローチを指す言葉です。
DevOpsという言葉が普及する前から、開発と運用の協力や自動化等が可能であったのと同じく、Agentic DevOpsという言葉が普及する前から、AIを活用した開発・運用の自動化や最適化の取り組みは一部で行われていた。クラウドや自動化技術の普及でDevOpsが普及したように、AIやエージェント技術の進化・普及によってAgentic DevOpsの普及が急速に進んでいます。

## Agentic DevOpsの幕開け
Agentic DevOpsは、プロジェクトの計画・要件定義、設計・構築・テスト、運用・保守といったあらゆる工程に適用可能であり、各段階でAIエージェントが支援や自動化、最適化を実現します。

## 主なAI活用領域の例
現場で活用されるAI技術例は以下が挙げられるます。
- GitHub Copilot
- M365 Copilot
- Azure OpenAI Service / Azure AI Foundry
- 生成AI以外のAI
- その他

### Azure OpenAI Service / Azure AI Foundry
GitHub Copilot（IDE内の補完 / チャット / テスト生成 / 小規模コード変換）は、多くの“日常的な開発タスク”や本研修スコープ（COBOL断片の理解→Java化の入門例）では十分な生産性向上をもたらします。一方で、レガシー大量資産の体系的移行や要件（ガバナンス・監査・品質標準化）によっては、Azure OpenAI Service や Azure AI Foundryを活用が有効となる場合があります。

#### Azure OpenAI Service / Azure AI Foundry 併用の目安例
（以下は一例であり、全てのケースを網羅するものではありません）
| 判定項目           | GitHub Copilotのみで十分                           | Azureも併用すべき                                 |
|--------------------|--------------------------------------------|---------------------------------------------------|
| 対象コード量       | 小～中 | 大規模 |
| 監査・ガバナンス   | 基本的な履歴で十分| 詳細な履歴が必要 |
| 社内ドキュメント参照 | GitHub Copilot Spacesで十分 | 大規模RAG統合や権限管理が必要 |
| ワークフロー自動化 | 多段ワークフローの場合に人が都度介入 | 多段ワークフロー自動化希望 |

#### サンプル: Legacy Modernization Agents リポジトリ
Azure サンプル（[Azure-Samples/Legacy-Modernization-Agents](https://github.com/Azure-Samples/Legacy-Modernization-Agents)）では、レガシー移行を支援する複数エージェント（例: ソース解析役 / 変換候補生成役 / 検証・評価役）をオーケストレーションし、ワークフロー化するアーキテクチャ例が示されています。これにより:
- 大量コードに対する分割統治（解析→変換→レビュー）
- エージェント間の役割分離による説明責任（“誰が何を判断したか”のログ化）
- 評価ループ（品質スコア / 再試行）による改善トラック
が可能となり、単純な IDE 内補助を超えた“組織的移行ファクトリ”の足場を構築できます。

[こちら](https://github.com/rukasakurai/Legacy-Modernization-Agents/tree/1-sample-code-hello-world-level/sample-code)では、シンプルなCOBOLアプリをJavaに変換しAzure上で動かした場合のサンプルコード（JavaとIaC）を紹介しています。

## 生成AI以外のAI活用
現在は生成AIが注目を集めていますが、生成AI以外のAI技術（OCRや機械学習など）も現場では引き続き有用です。例えば、
- Azure AI Search（検索）
- Azure AI Document Intelligence（ドキュメントや画像からの情報抽出）
- Azure Speech Services（音声認識・合成）
- Azure Monitor（Kusto Query Languageによる時系列異常検知：series_decompose_anomalies関数など）

## インストラクター向けノート
> **所要時間目安**
> - 120分
